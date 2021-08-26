import pandas as pd


# DNI	NOMBRES	AP_PATERNO	AP_MATERNO	COD_POSTULANTE	COD_PROGRAMA	COD_FACULTAD
path = "data/LISTA EXAMEN.xlsx"
df = pd.read_excel(path, names=[
    "dni", "nombre", "apellido_paterno", 
    "apellido_materno", "codigo_postulante",
    "codigo_programa", "codigo_upg"])


# ## test cod postulante unico

df = df.applymap(str)

df['dni'] = df['dni'].str.strip().str.upper()
df['nombre'] = df['nombre'].str.strip().str.upper()
df['apellido_paterno'] = df['apellido_paterno'].str.strip().str.upper()
df['apellido_materno'] = df['apellido_materno'].str.strip().str.upper()
df['codigo_postulante'] = df['codigo_postulante'].str.strip()
df['codigo_programa'] = df['codigo_programa'].str.strip()
df['codigo_upg'] = df['codigo_upg'].str.strip()

# duplicates = df[df.duplicated(['dni'], keep=False)]

# duplicates.to_csv("duplicados-por-dni.csv")

# assert len(duplicates) == 0

# df = df.drop_duplicates(["dni"]) # TODO: delete this (when fixed)
fullNames = df['apellido_paterno'] + ' ' + df['apellido_materno'] + ' ' + df['nombre'] 

df["full_name"] = fullNames
# # print(df[df["full_name"] == "AQUINO HANCCO GLADYS"])
# print(fullNames.value_counts())

assert all(fullNames.value_counts() == 1)
assert all(df["dni"].value_counts() == 1)

bad_dni = df[df["dni"].str.len() != 8]
bad_dni.to_csv("dni-diferente-a-8-digitos.csv")

# df = df[df["dni"].str.len() == 8] # TODO: delete this (when fixed)

# assert all(df["dni"].str.len() == 8)

assert all(df["codigo_postulante"].value_counts() == 1)

assert all(df['codigo_programa'].str.len() == 6)
postulantes = df.to_dict("records")

def demos():
    # return [
    #     { 
    #         'nombre': f"NOMBRE {i}", 
    #         'apellido_paterno': f"APE {i}", 
    #         'apellido_materno': f"AM {i}", 
    #         'dni': f'1001000{i}', 
    #         'codigo_postulante': f'1000{i}', 
    #         'codigo_programa': '111111',
    #         'codigo_upg':'1111' 
    #     }
    #     for i in range(1, 9 + 1)
    # ]

    return [
        {
            "dni": "73646447",
            "codigo_postulante": "12123123",
            "apellido_paterno": "mauricio",
            "apellido_materno": "delgadillo",
            "nombre": "arthur",
            "codigo_programa": "1",
            "codigo_upg": "12"
        },
        {
            "dni": "48484489",
            "codigo_postulante": "12312390",
            "apellido_paterno": "quintanilla",
            "apellido_materno": "perez",
            "nombre": "diana",
            "codigo_programa": "1",
            "codigo_upg": "12"
        }
    ]

def seed_users():
    data = [
        *postulantes,
        # *demos(),
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


# psql -h localhost  -U  postgres -p 5432 -d exams -f  data/insert_postulants.sql

# For admin execute something like:
# INSERT INTO user_account (name, password, role) VALUES ('unmsm-admin', 'unsuperpasword', 'ADMIN');
