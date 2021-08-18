import pandas as pd

path = "data/resultados_27_03_2021_sin_puntaje_negativo.xlsx"
columns = ['DNI', 'Cod. Postulante', 'Apellidos y Nombres','UPG', 'Programa', 'Puntaje',
              'Fecha inicio', 'Fecha final']

df = pd.read_excel(path, skiprows=1, usecols = columns)
df = df.replace(regex=r'^\*', value='')

# eliminar demo
# { "codigo_upg": { $eq: "1111" } }

df = df[df["UPG"] != 1111]

df["aprobo?"] = df["Puntaje"] >= 55

df["noDioExamen"] = df["Fecha inicio"] == "-"

print("total", len(df))
# df.to_csv("resultados_resumido.csv")

aprobados = df[df["aprobo?"]]
print("aprobados", len(aprobados))
desaprobados = df[~df["aprobo?"]]
print("desaprobados", len(desaprobados))
print("no dio examen:", len(df[df["noDioExamen"]]))
print(df[["Puntaje"]].describe())

# print(df.head())