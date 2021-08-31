
alter table postulant_question add column alternatives bigint[];

update postulant_question
set alternatives = ARRAY (
    select epr.exam_part_id
    from postulant_question_alternative_references pqar 
    join exam_part_reference epr on epr.id = pqar.alternative_references_id
    where pqar.postulant_question_id = postulant_question.id
    order by epr.number
);

delete exam_part_reference epr
from postulant_question_alternative_references pqar
where epr.id = pqar.alternative_references_id;

delete from postulant_question_alternative_references;
