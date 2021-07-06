package org.ouracademy.exams.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class PostulantQuestion {
    // POSSIBLE DELETE USE COMPOSITE primary key(question_id, postulant_id)
    @Id
    Long id;
    // ---
    
    @ManyToOne
    Question question;
    //@OneToMany
    @Transient
    List<ExamPart> alternatives; // C, D, A, B
    @ManyToOne
    ExamPart postulantAnswer; // A <= postulant edit this

    PostulantQuestion(Question question, List<ExamPart> alternatives) {
        this.question = question;
        this.alternatives = alternatives;
    }

    public boolean isCorrect() {
        return question.answer.equals(postulantAnswer);
    }

    public boolean isWrong() {
        return !isCorrect();
    }
}