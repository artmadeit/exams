package org.ouracademy.exams.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.ouracademy.exams.domain.structure.ExamPart;
import org.ouracademy.exams.domain.structure.Question;
import org.ouracademy.exams.utils.NotFoundException;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

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
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("number")
    List<ExamPartReference> alternativeReferences = new ArrayList<>();

    @LastModifiedDate 
    @Setter private Instant lastModifiedDate;
    
    /**
     * @apiNote jpa only
     */
    PostulantQuestion() {}

    public PostulantQuestion(Integer number, Question question, List<ExamPart> alternatives) {
        super(question, number);
        this.alternativeReferences = ExamPartReference.toReferences(alternatives);
    }

    public Question getQuestion() {
        return (Question) this.examPart;
    }

    public Double getScore() {
        return getQuestion().score(getPostulantAnswer());
    }

    public Optional<ExamPart> getAlternative(Long alternativeId) {
        return getAlternatives()
            .stream()
            .filter(x -> x.getId().equals(alternativeId))
            .findFirst();
    }

    public List<ExamPart> getAlternatives() {
        return this.alternativeReferences.stream()
            .map(ExamPartReference::getExamPart)
            .collect(Collectors.toList());
    }

    /**
     * @param alternativeId must be in the alternatives of this question
     */
    public void updateAnswer(Long alternativeId) {
        postulantExam.assertHasNotEnded();

        var alternative = alternativeId == null? 
            null:
            getAlternative(alternativeId).orElseThrow(() -> new NotFoundException("alternative_in_question", new Object[] {alternativeId, id} ));
        
        this.postulantAnswer = alternative;
    }
}