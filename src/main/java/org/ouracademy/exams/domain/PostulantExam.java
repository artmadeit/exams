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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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
    @Embedded
    DateTimeRange actualRange;

    @LazyCollection(LazyCollectionOption.EXTRA)
    @OneToMany(mappedBy="postulantExam", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("number")
    List<PostulantQuestion> questions = new ArrayList<>();
    
    
    // Used with lazycollection extra, see: https://stackoverflow.com/questions/4230355/jpa-directly-mapping-collection-size-to-attribute-using-count
    public Integer getNumberOfQuestions() {
    	return questions.size();
    }

    /**
     * @apiNote jpa only
     */
    PostulantExam() {}

    @Builder
    public PostulantExam(Inscription inscription, List<PostulantQuestion> questions) {
        this.inscription = inscription;
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
        if(inscription.getEvent().hasEnded())
            throw new ExamEvent.EndedException(inscription.getEvent());
        
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
