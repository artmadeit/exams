package org.ouracademy.exams.domain;

import java.util.List;

public class Pregunta extends ExamPart {
    Double score;
    ExamPart answer;

    public Pregunta() {
        this.type = Type.QUESTION;
    }
    
    List<ExamPart> alternativas() {
        return this.getChilds();
    }
}
