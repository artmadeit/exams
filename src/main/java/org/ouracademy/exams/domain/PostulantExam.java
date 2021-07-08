package org.ouracademy.exams.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.ouracademy.exams.event.ExamEvent;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class PostulantExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Postulant postulant;
    @ManyToOne
    ExamEvent event;
    @Embedded
    DateTimeRange actualRange;
    @OneToMany(mappedBy="postulantExam", cascade = CascadeType.ALL, orphanRemoval = true)
    List<PostulantQuestion> questions = new ArrayList<>();

    /**
     * @apiNote jpa only
     */
    PostulantExam() {}

    @Builder
    public PostulantExam(Postulant postulant, ExamEvent event, List<PostulantQuestion> questions) {
        this.postulant = postulant;
        this.event = event;
        this.actualRange = new DateTimeRange(LocalDateTime.now(), null);
        setQuestions(questions);
    }

    private void setQuestions(List<PostulantQuestion> questions) {
        for (PostulantQuestion postulantQuestion : questions) {
            addPostulantQuestion(postulantQuestion);
        }
    }

    private void addPostulantQuestion(PostulantQuestion postulantQuestion) {
        this.questions.add(postulantQuestion);
        postulantQuestion.setPostulantExam(this);
    }

    public void finish() {
        this.actualRange = new DateTimeRange(this.actualRange.start, LocalDateTime.now());
    }
}
