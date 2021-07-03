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
    ExamPart exam;
    @ManyToOne
    Postulante postulante;
    @ManyToOne
    ExamSpecification specification;
    @Embedded
    DateTimeRange actualRange;
    
    List<PostulantAnswer> answers = new ArrayList<>();

    @Builder
    public PostulantExam(ExamPart examPart, 
            Postulante postulante,
            ExamSpecification specification) {
        this.exam = examPart;
        this.postulante = postulante;
        this.specification = specification;
        this.actualRange = new DateTimeRange(LocalDateTime.now(), DateTimeRange.INFINITY);
    }

    // add & edit answer
    // la respuesta debe pertenecer al examen sino no tendria sentido

    class PostulantAnswer {
        Pregunta pregunta;
        TextContent respuesta;

        public boolean isCorrect() {
            return pregunta.respuesta.equals(respuesta);
        }

        public boolean isWrong() {
            return !isCorrect();
        }
    }
}
