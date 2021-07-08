package org.ouracademy.exams.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Getter;

@Getter
@Entity
public class Question extends ExamPart {
    Double score = 4.0;
    @OneToOne
    ExamPart answer; // B    

    public Question() {
        this.type = Type.QUESTION;
    }
    
    List<ExamPart> alternatives() {// A, B, C, D
        return this.getChilds();
    }
}
