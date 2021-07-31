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

    public Question(String content, ExamPart parent) {
        // TODO: assert parent is not a final child
        super(Type.QUESTION, null, content);
        this.parent = parent;
    }
    
    public static ExamPart alternative(String content, Question parent) {
        var result = new ExamPart(Type.ALTERNATIVE, null, content);
        result.setParent(parent);
        return result;
    }

    public List<ExamPart> alternatives() {// A, B, C, D
        return this.getChilds();
    }
}
