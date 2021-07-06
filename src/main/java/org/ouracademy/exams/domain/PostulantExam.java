package org.ouracademy.exams.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Builder;

@Entity
public class PostulantExam {
    @Id
    Long id;
    @ManyToOne
    Postulant postulant;
    @ManyToOne
    ExamEvent event;
    @Embedded
    DateTimeRange actualRange;
    @OneToMany(mappedBy="postulantExam")
    List<PostulantQuestion> questions = new ArrayList<>();

    @Builder
    public PostulantExam(Postulant postulant, ExamEvent event, List<PostulantQuestion> questions) {
        this.postulant = postulant;
        this.event = event;
        this.actualRange = new DateTimeRange(LocalDateTime.now(), null);
        this.questions = questions;
    }
}
