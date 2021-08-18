CREATE VIEW question_with_root AS 
WITH RECURSIVE cte_name AS(
    SELECT ep.id as root_id, ep.id, ep.content, ep.parent_id, ep.type, ep.childs_order
    FROM exam_part ep
    WHERE parent_id IS NULL
    
    UNION
    
    SELECT p.root_id, child.id, child.content, child.parent_id, child.type, child.childs_order
    FROM exam_part child JOIN cte_name p ON child.parent_id = p.id
    
) 
SELECT *, ROW_NUMBER() OVER (ORDER BY id) as question_number
FROM cte_name c
WHERE c.type = 'QUESTION'
ORDER BY c.root_id, c.parent_id, c.childs_order;



CREATE VIEW postulant_question_with_answers AS
SELECT qwr.question_number, pq.id, q.answer_id as expected_answer_id, pq.postulant_answer_id
FROM question_with_root qwr
JOIN question q ON (q.id = qwr.id)
JOIN exam_part_reference epr ON (epr.exam_part_id = qwr.id)
JOIN postulant_question pq ON (epr.id = pq.id)
ORDER BY question_number;



SELECT 
question_number, 
COUNT(pq.id) as total,
SUM(CASE WHEN expected_answer_id = postulant_answer_id THEN 1 ELSE 0 end) / COUNT(pq.id) * 1.0 As ratio_ok
FROM postulant_question_with_answers pq
GROUP BY question_number;
