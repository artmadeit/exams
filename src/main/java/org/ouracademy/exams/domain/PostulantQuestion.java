package org.ouracademy.exams.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;

import com.vladmihalcea.hibernate.type.array.ListArrayType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.ouracademy.exams.domain.structure.ExamPart;
import org.ouracademy.exams.domain.structure.Question;
import org.ouracademy.exams.utils.NotFoundException;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;


@TypeDef(
    name = "list-array",
    typeClass = ListArrayType.class
)
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class PostulantQuestion extends ExamPartReference {    
    @ManyToOne
    @Setter PostulantExam postulantExam;

    /**
     * Can be null if no answer is responded by the postulant
     * e.g, if alternatives are A, B, C, D
     * postulantAnswer of diana can be C 
     * and arthur can be none.
     */
    @ManyToOne
    @Setter ExamPart postulantAnswer;

    @LastModifiedDate 
    @Setter private Instant lastModifiedDate;

    @Type(type = "list-array")
    @Column(name = "alternatives", columnDefinition = "bigint[]")
    List<Long> alternativeIds = new ArrayList<>();

    /**
     * @apiNote jpa only
     */
    PostulantQuestion() {}

    public PostulantQuestion(Integer number, Question question, List<ExamPart> alternatives) {
        super(question, number);
        this.alternativeIds = alternatives.stream().map(ExamPart::getId).toList();
    }

    public Question getQuestion() {
        return (Question) this.examPart;
    }

    public ExamPart getAnswer() {
        return getQuestion().getAnswer();
    }

    public Double getScore() {
        return getQuestion().score(getPostulantAnswer());
    }

    /**
     * @param alternativeId must be in the alternatives of this question
     */
    public void updateAnswer(ExamPart alternative) {
        postulantExam.assertHasNotEnded();

        if (alternative != null && !this.alternativeIds.contains(alternative.getId())) 
            throw new NotFoundException("alternative_in_question");
        
        this.postulantAnswer = alternative;
    }
}