package org.ouracademy.exams.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.ouracademy.exams.domain.structure.ExamPart;
import org.ouracademy.exams.domain.structure.Question;
import org.ouracademy.exams.utils.NotFoundException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class PostulantQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @ManyToOne
    Question question;
    
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
    
    @ManyToMany
    List<ExamPart> alternatives = new ArrayList<>();
    
    /**
     * @apiNote jpa only
     */
    PostulantQuestion() {}

    public PostulantQuestion(Question question, List<ExamPart> alternatives) {
        this.question = question;
        this.alternatives = alternatives;
    }

    public boolean isCorrect() {
        return question.getAnswer().equals(postulantAnswer);
    }

    public boolean isWrong() {
        return !isCorrect();
    }

    public Optional<ExamPart> getAlternative(Long alternativeId) {
        return this.alternatives.stream()
            .filter(x -> x.getId().equals(alternativeId))
            .findFirst();
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