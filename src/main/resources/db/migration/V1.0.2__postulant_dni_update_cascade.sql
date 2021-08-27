alter table postulant drop constraint "fk1t3vkmp6s21suftn8gsfenc52";
alter table postulant add constraint "fk1t3vkmp6s21suftn8gsfenc52" FOREIGN KEY (name) REFERENCES user_account(name) on update cascade;

alter table inscription drop constraint "fkeootrlv6d09mbetjehk6k03l8";
alter table inscription add CONSTRAINT "fkeootrlv6d09mbetjehk6k03l8" FOREIGN KEY (postulant_name) REFERENCES postulant(name) on update cascade;