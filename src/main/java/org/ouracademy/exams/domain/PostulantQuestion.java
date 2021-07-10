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

    @ManyToOne
    @Setter ExamPart postulantAnswer; // B <= postulant edit this
    
    @ManyToMany
    List<ExamPart> alternatives = new ArrayList<>(); // C, D, A, B
    
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
}