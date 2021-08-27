import pandas as pd

path = "data/resultados_27_03_2021_sin_puntaje_negativo.xlsx"
columns = ['DNI', 'Cod. Postulante', 'Apellidos y Nombres','UPG', 'Programa', 'Puntaje',
              'Fecha inicio', 'Fecha final']

df = pd.read_excel(path, skiprows=1, usecols = columns)
df = df.replace(regex=r'^\*', value='')

# eliminar demo
# { "codigo_upg": { $eq: "1111" } }

df = df[df["UPG"] != 1111]

df["esta_aprobado"] = df["Puntaje"] >= 55

df["es_ausente"] = df["Fecha inicio"] == "-"

print("total", len(df))
# df.to_csv("resultados_resumido.csv")

aprobados = df[df["esta_aprobado"]]
print("aprobados", len(aprobados))
desaprobados = df[~df["esta_aprobado"]]
print("desaprobados", len(desaprobados))
print("Ausentes:", len(df[df["es_ausente"]]))
print(df[["Puntaje"]].describe())

# print(df.head())

result = df.groupby("UPG").aggregate({
    "DNI": ["count"],
    "es_ausente": ["count"],
    "esta_aprobado": ["count"]
})

# result["Inscritos"] = df_grouped["DNI"].count()
# result["Ausentes"] = df_grouped["es_ausente"].count()
# result["Postulantes"] = result["Inscritos"] - result["Ausentes"] 
# result["Ingresantes"] = result["esta_aprobado"].count()
# result["Ratio de aprobados"] = result["Ingresantes"] / result["Postulantes"]
# result["Ratio de asistencia"] = result["Postulantes"] / result["Inscritos"]
# print(result)
