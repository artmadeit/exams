package org.ouracademy.exams.domain;

import java.util.List;

// un examen => secciones
// una seccion => textos | preguntas
// una texto => preguntas
public class ExamPartContainer extends ExamPart {

    public enum Type {
        EXAM, SECTION, TEXT;
    }

    Type type;
    String titulo;


    public List<ExamPart> getQuestions(Type section, String sectionName) {
        return null;
    }
}
