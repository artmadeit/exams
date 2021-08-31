
alter table postulant_question_alternative_references drop constraint "fk9rasje04epxbqapbm205n9tfa";

alter TABLE postulant_question_alternative_references add CONSTRAINT "fk9rasje04epxbqapbm205n9tfa" FOREIGN KEY (alternative_references_id) REFERENCES exam_part_reference(id) on delete cascade;

alter table postulant_question add column alternatives bigint[];

update postulant_question
set alternatives = ARRAY (
    select epr.exam_part_id
    from postulant_question_alternative_references pqar 
    join exam_part_reference epr on epr.id = pqar.alternative_references_id
    where pqar.postulant_question_id = postulant_question.id
    order by epr.number
);

delete from exam_part_reference
using postulant_question_alternative_references pqar
where exam_part_reference.id = pqar.alternative_references_id;

drop table postulant_question_alternative_references; 
