create view asistentes_y_ingresantes_summary as

select 
upg_code,
program_code,
inscritos,
ausentes,
asistieron,
ingresantes,
ingresantes / nullif(asistieron, 0) as ratio_aprobados,
asistieron / nullif(inscritos, 0) as ratio_asistencia
from (
    select
    upg_code,
    program_code,
    COUNT(name) as inscritos,
    SUM(CASE WHEN is_absent then 1.0 else 0.0 end) as ausentes,
    SUM(CASE WHEN not is_absent then 1.0 else 0.0 end) as asistieron,
    SUM(CASE WHEN score >= 55.0 then 1.0 else 0.0 end) as ingresantes
    from exam_summary
    group by upg_code, program_code order by upg_code asc, program_code asc
) as postulante_grouped;