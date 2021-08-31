
def demos():
    return [
        { 
            'nombre': f"NOMBRE {i}", 
            'apellido_paterno': f"APE {i}", 
            'apellido_materno': f"AM {i}", 
            'dni': f'1001000{i}', 
            'codigo_postulante': f'1000{i}', 
            'codigo_programa': '111111',
            'codigo_upg':'1111' 
        }
        for i in range(1, 20_000 + 1)
    ]

def seed_users():
    data = [
        # *postulantes,
        *demos(),
    ]

    print("se debe registrar (consulte en su bd)")
    print(len(data))
    
    insert_account = "INSERT INTO user_account (name, password, role) \
        VALUES \n" + ",\n".join((f"('{p['dni']}', '{p['codigo_postulante']}', 'POSTULANT')" for p in data )) + ";"

    insert_postulant = "INSERT INTO postulant (name, first_name, last_name, mother_last_name, program_code, upg_code) \
        VALUES \n" + ",\n".join((f"('{p['dni']}', '{p['nombre']}', '{p['apellido_paterno']}', '{p['apellido_materno']}', '{p['codigo_programa']}', '{p['codigo_upg']}')" for p in data )) + ";"

    insert_inscription = "INSERT INTO inscription (postulant_name, event_id) \
        VALUES \n" + ",\n".join((f"('{p['dni']}', 1)" for p in data )) + ";"
      
    sql = insert_account + "\n" \
        + insert_postulant + "\n" \
        + insert_inscription

    with open("data/insert_postulants.sql", "w") as sql_script:
        sql_script.write(sql)


seed_users()

# For admin execute something like:
# INSERT INTO user_account (name, password, role) VALUES ('unmsm-admin', 'unsuperpasword', 'ADMIN');

# INSERT INTO exam_event (description, date_time_end, date_time_start, title) VALUES ('demo','2024-08-28 14:33:00','2021-08-28 14:21:00', 'prueba');

# psql -h localhost  -U  postgres -p 5432 -d exams -f  seeder/data/insert_postulants.sql
# psql -h localhost  -U  postgres -p 5432 -d exams -f  seeder/test_exam_templates.sql



