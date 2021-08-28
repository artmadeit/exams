CREATE or replace VIEW postulant_question_with_answers AS
SELECT qwr.root_id as exam_id, qwr.question_number, q.answer_id as expected_answer_id, pq.*,
q.blank_score, q.correct_score, q.incorrect_score
FROM question_with_root qwr
JOIN question q ON (q.id = qwr.id)
JOIN exam_part_reference epr ON (epr.exam_part_id = qwr.id)
JOIN postulant_question pq ON (epr.id = pq.id)
join postulant_exam ep on (pq.postulant_exam_id = ep.id)
join inscription i on (ep.inscription_id = i.id)
join postulant post on (post.name = i.postulant_name)
where post.upg_code <> 'UPG_PRUEBA'
ORDER BY question_number;