
alter table inscription add column postulant_exam_id bigint;

update inscription set postulant_exam_id = postulant_exam.id 
from postulant_exam where postulant_exam.inscription_id = inscription.id;

alter table inscription add constraint "postulant_exam_fk" foreign key (postulant_exam_id) references postulant_exam(id);



CREATE or replace VIEW postulant_question_with_answers AS
SELECT qwr.root_id as exam_id, qwr.question_number, q.answer_id as expected_answer_id, pq.*,
q.blank_score, q.correct_score, q.incorrect_score
FROM question_with_root qwr
JOIN question q ON (q.id = qwr.id)
JOIN exam_part_reference epr ON (epr.exam_part_id = qwr.id)
JOIN postulant_question pq ON (epr.id = pq.id)
join postulant_exam ep on (pq.postulant_exam_id = ep.id) -- here change
join inscription i on (ep.id = i.postulant_exam_id)
join postulant post on (post.name = i.postulant_name)
where post.upg_code <> 'UPG_PRUEBA'
ORDER BY question_number;


CREATE or replace VIEW exam_summary AS 
    SELECT 
    postulant.name,
    postulant.program_code,
    postulant.upg_code,
    e.id is null as is_absent,
    es.score
    FROM exam_with_score es
    JOIN postulant_exam e ON es.postulant_exam_id = e.id
    RIGHT JOIN inscription i on e.id = i.postulant_exam_id  -- here change
    RIGHT JOIN postulant ON postulant.name = i.postulant_name;



alter table postulant_exam drop column inscription_id;

