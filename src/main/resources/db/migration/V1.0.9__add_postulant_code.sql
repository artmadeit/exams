alter table postulant add column code varchar(50);

update postulant
set code = user_account.password
from user_account
where user_account.name = postulant.name;

alter table postulant add constraint code_unique unique (code);
