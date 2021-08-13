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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

import org.ouracademy.exams.domain.event.ExamEvent;
import org.ouracademy.exams.domain.postulant.Postulant;
import org.ouracademy.exams.utils.BadArgumentsException;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class PostulantExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne
    Inscription inscription;
    @ManyToOne
    ExamEvent event;
    @Embedded
    DateTimeRange actualRange;

    @OneToMany(mappedBy="postulantExam", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("number")
    List<PostulantQuestion> questions = new ArrayList<>();

    /**
     * @apiNote jpa only
     */
    PostulantExam() {}

    @Builder
    public PostulantExam(Inscription inscription, ExamEvent event, List<PostulantQuestion> questions) {
        this.inscription = inscription;
        this.event = event;
        this.actualRange = new DateTimeRange(LocalDateTime.now(), null);
        setQuestions(questions);
    }

    public Postulant getPostulant() {
        return this.inscription.postulant;
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
        assertHasNotEnded();
        this.actualRange = new DateTimeRange(this.actualRange.start, LocalDateTime.now());
    }

    public void assertHasNotEnded() {
        if(event.hasEnded())
            throw new ExamEvent.EndedException(event);
        
        if(actualRange.hasEnded())
            throw new BadArgumentsException("exam.ended");
    }

    public Double getScore() {
        return this.questions.stream().mapToDouble(PostulantQuestion::getScore).sum();
    }

    public Boolean isApproved() {
        return getScore() >= 55.0;
    }
}
