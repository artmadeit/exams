package org.ouracademy.exams.domain.structure;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Getter;

@Getter
@Entity
public class Question extends ExamPart {
    Double correctScore = 4.0;
    Double blankScore = 0.0;
    Double incorrectScore = -1.0;

    @OneToOne
    ExamPart answer; // B    

    
    /**
     * @apiNote jpa only
     */
    Question() {}

    public Question(String content) {
        super(Type.QUESTION, null, content);
    }
    
    public static ExamPart alternative(String content) {
        return new ExamPart(Type.ALTERNATIVE, null, content);
    }

    public void markAsAnswer(int alternativeNumber) {
        this.answer = this.alternatives().get(alternativeNumber - 1);
    }

    public List<ExamPart> alternatives() {// A, B, C, D
        return this.getChilds();
    }

    public Double score(ExamPart postulantAnswer) {
        if (answer.equals(postulantAnswer)) return correctScore;
        if (postulantAnswer == null) return blankScore;
        return incorrectScore;
    }
}
