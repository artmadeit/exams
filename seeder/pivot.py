import pandas as pd


# select postulant.name as dni, pqwa.exam_id, pqwa.question_number, ep.childs_order + 1 as answer_number  
# from postulant_question_with_answers pqwa
# JOIN postulant_exam e ON pqwa.postulant_exam_id = e.id
# LEFT JOIN exam_part ep ON pqwa.postulant_answer_id = ep.id
# RIGHT JOIN inscription i ON e.id = i.postulant_exam_id 
# RIGHT JOIN postulant ON postulant.name = i.postulant_name;

df = pd.read_csv("query_result_2021-08-31T05_04_00.382Z.csv")
df["exam_id"] = df["exam_id"].map({1: "plantilla 1", 133: "plantilla 2"})

print("Leyenda: answer_number 1 => A, 2 => B, 3 => C, 4 => D")
summary = df.pivot_table(index="dni", columns=["exam_id", "question_number"], values="answer_number")
print(
    summary
)
# summary.to_csv("hello.csv", index=None)