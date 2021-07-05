package org.ouracademy.exams.domain;

import java.util.List;

public class Question extends ExamPart {
    Double score;
    ExamPart answer; // B    

    public Question() {
        this.type = Type.QUESTION;
    }
    
    List<ExamPart> alternatives() {// A, B, C, D
        return this.getChilds();
    }
}
