package org.ouracademy.exams.domain;

import java.util.List;

public class Question extends ExamPart {
    Double score;
    ExamPart answer;

    public Question() {
        this.type = Type.QUESTION;
    }
    
    List<ExamPart> alternatives() {
        return this.getChilds();
    }
}
