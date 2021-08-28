CREATE VIEW exam_with_score AS 
    SELECT pq.postulant_exam_id,
    SUM(
        CASE 
        WHEN postulant_answer_id is null THEN blank_score
        WHEN expected_answer_id = postulant_answer_id THEN correct_score 
        ELSE incorrect_score
        end) as score
    FROM postulant_question_with_answers pq
    GROUP BY pq.postulant_exam_id;


CREATE VIEW exam_summary AS 
    SELECT 
    postulant.name,
    postulant.program_code,
    postulant.upg_code,
    e.id is null as is_absent,
    es.score
    FROM exam_with_score es
    JOIN postulant_exam e ON es.postulant_exam_id = e.id
    RIGHT JOIN inscription i on e.inscription_id = i.id 
    RIGHT JOIN postulant ON postulant.name = i.postulant_name;
