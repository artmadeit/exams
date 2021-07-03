package org.ouracademy.exams.domain;

import java.util.List;

// un examen => secciones
// una seccion => textos | preguntas
// una texto => preguntas
public class ExamPart extends TextContent {

    public enum Type {
        EXAM, SECTION, TEXT;
    }

    Type type;
    String titulo;


    public List<TextContent> getQuestions(Type section, String sectionName) {
        return null;
    }
}
