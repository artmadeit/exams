package org.ouracademy.exams.domain.structure;

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

    
    /**
     * @apiNote jpa only
     */
    Question() {}

    public Question(String content, ExamPart parent) {
        super(Type.QUESTION, null, content);
        if(parent.type.equals(Type.QUESTION) || parent.type.equals(Type.ALTERNATIVE))
            throw new IllegalArgumentException("parent can't be another question or an alternative");
        
        setParent(parent);
    }
    
    public static ExamPart alternative(String content, Question parent) {
        var result = new ExamPart(Type.ALTERNATIVE, null, content);
        result.setParent(parent);
        return result;
    }

    public void markAsAnswer(int alternativeNumber) {
        this.answer = this.alternatives().get(alternativeNumber - 1);
    }

    public List<ExamPart> alternatives() {// A, B, C, D
        return this.getChilds();
    }
}
