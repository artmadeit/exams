package org.ouracademy.exams.domain;

import java.util.List;

public class Pregunta extends ExamPart {
    Double score;
    ExamPart respuesta;

    public Pregunta() {
        this.type = Type.QUESTION;
    }
    
    List<ExamPart> alternativas() {
        return this.childs;
    }
}
