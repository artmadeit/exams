import pandas as pd

path = "data/resultados_27_03_2021_sin_puntaje_negativo.xlsx"

df = pd.read_excel(path, skiprows=1)
df = df.replace(regex=r'^\*', value='')
print(df["UPG"].value_counts())
print(df["Programa"].value_counts())

demos = df[df["Programa"] == '111111']
print(demos[['Programa','UPG','DNI', 'Cod. Postulante']])
# eliminar demo
# { "codigo_upg": { $eq: "1111" } }
df = df[df["DNI"] != "11111111"]

correct = 1
incorrect = -1
blank = 0
not_correspond = 2

def ratio_corrects(df, question_number):
    question = df[question_number]
    total_correspond = len(question[question != not_correspond])
    ratio = -1 if total_correspond == 0 else len(question[question == correct]) / total_correspond
    
    return round(ratio, 2), total_correspond

def print_ratios(df, result, info):
    questions = range(1, 50 + 1)
    for question_number in questions:
        ratio, total_correspond  = ratio_corrects(df, question_number)
        result.append({
            **info,
            "question": question_number,
            "ratio": ratio, 
            "total": total_correspond
        })

result = []
for UPG, df_group in df.groupby("UPG"):
    print("UPG " + str(UPG) + ":")
    print_ratios(df_group, result, { "upg": UPG})

df_ratios = pd.DataFrame.from_records(result)
df_ratios.to_csv("data/ratios-por-upg_sin_puntaje_negativo.csv", index=False)


# ......
result = []
print_ratios(df, result, {})
df_ratios_universidad = pd.DataFrame.from_records(result)
df_ratios_universidad.to_csv("data/ratios-universidad_sin_puntaje_negativo.csv", index=False)
