-- SELECT count(*) FROM "public"."postulant_exam" join inscription on postulant_exam.inscription_id = inscription.id where postulant_exam.event_id = inscription.event_id;

alter table postulant_exam drop column event_id; 
