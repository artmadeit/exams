from router import db
import pandas as pd


# DNI	NOMBRES	AP_PATERNO	AP_MATERNO	COD_POSTULANTE	COD_PROGRAMA	COD_FACULTAD
path = "data/examen_27_03_2021.xlsx"
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

duplicates = df[df.duplicated(['dni'], keep=False)]

# duplicates.to_csv("duplicados-por-dni.csv")

# df = df.drop_duplicates(["dni"]) # TODO: delete this (when fixed)
fullNames = df['apellido_paterno'] + ' ' + df['apellido_materno'] + ' ' + df['nombre'] 

# df["full_name"] = fullNames
# print(df[df["full_name"] == "AQUINO HANCCO GLADYS"])
# print(fullNames.value_counts())

# assert all(fullNames.value_counts() == 1)
assert all(df["dni"].value_counts() == 1)

# bad_dni = df[df["dni"].str.len() != 8]
# bad_dni.to_csv("dni-diferente-a-8-digitos.csv")

# # df = df[df["dni"].str.len() == 8] # TODO: delete this (when fixed)

# assert all(df["dni"].str.len() == 8)

assert all(df["codigo_postulante"].value_counts() == 1)

# assert all(df['codigo_programa'].str.len() == 6)
postulantes = df.to_dict("records")

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
        for i in range(1, 9 + 1)
    ]

def seed_users():

    db.postulante.delete_many({})
    db.admin.delete_many({})

    db.admin.insert_one({'usuario': 'admin', 'contraseña': 'MCbY/-vV7qL5Xj9Hzq^['})

    db.postulante.create_index("codigo_postulante", unique = True)
    db.postulante.create_index("dni", unique = True)

    db.postulante.insert_many([
        *postulantes,
        *demos(),
    ])

print("se debe registrar (consulte en su bd)")
print(len([        *postulantes, *demos() ]))
# seed_users()