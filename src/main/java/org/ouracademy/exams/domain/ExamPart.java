package org.ouracademy.exams.domain;

// un examen => secciones
// una seccion => textos | preguntas
// una texto => preguntas
public class ExamPart extends TextContent {

    public enum Type {
        EXAM, SECTION, TEXT;
    }

    Type type;
    String titulo;

    public PostulantExam start(Postulante postulante, ExamSpecification specification) {
        return PostulantExam.builder()
            .examPart(this)
            .postulante(postulante)
            .specification(specification).build();
    }
}
