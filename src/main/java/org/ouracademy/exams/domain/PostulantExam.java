package org.ouracademy.exams.domain;

import java.time.LocalDateTime;
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
    ExamEvent event;
    @Embedded
    DateTimeRange actualRange;
    List<PostulantQuestion> questions;

    @Builder
    public PostulantExam(Postulante postulant, ExamEvent event, List<PostulantQuestion> questions) {
        this.postulant = postulant;
        this.event = event;
        this.actualRange = new DateTimeRange(LocalDateTime.now(), null);
        this.questions = questions;
    }
}
