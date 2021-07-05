package org.ouracademy.exams.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Builder;

@Entity
public class PostulantExam {
    @Id
    Long id;
    @ManyToOne
    Postulante postulant;
    @ManyToOne
    ExamSpecification specification;
    @Embedded
    DateTimeRange actualRange;
    
    List<PostulantQuestion> questions;

    @Builder
    public PostulantExam(Postulante postulant, ExamSpecification specification, List<PostulantQuestion> questions) {
        this.postulant = postulant;
        this.specification = specification;
        this.actualRange = new DateTimeRange(LocalDateTime.now(), null);
        this.questions = questions;
    }

    // add & edit answer
    // la respuesta debe pertenecer al examen sino no tendria sentido

    public static class PostulantQuestion {
        Question question;
        List<ExamPart> alternativas;
        ExamPart postulantAnswer;

        PostulantQuestion(Question question, List<ExamPart> alternativas) {
            this.question = question;
            this.alternativas = alternativas;
        }

        public boolean isCorrect() {
            return question.answer.equals(postulantAnswer);
        }

        public boolean isWrong() {
            return !isCorrect();
        }
    }
}
