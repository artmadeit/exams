package org.ouracademy.exams.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.ouracademy.exams.domain.event.ExamEvent;
import org.ouracademy.exams.domain.postulant.Postulant;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Inscription {

    // TODO: id should be composite key (postulant_id, event_id)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @ManyToOne
    Postulant postulant;

    @ManyToOne
    ExamEvent event;

    @OneToOne(mappedBy = "inscription", cascade = CascadeType.ALL, optional = false)
    @Setter PostulantExam postulantExam;
    
    
    /**
     * @apiNote jpa only
     */
    Inscription() {}

    public Inscription(Postulant postulant, ExamEvent examEvent) {
        this.postulant = postulant;
        this.event = examEvent;
    }


    public PostulantExam startExam(List<PostulantQuestion> questions) {
        if (!event.hasStarted())
            throw new ExamEvent.NotStartedException(event);

        if (event.hasEnded())
            throw new ExamEvent.EndedException(event);

        return PostulantExam.builder()
            .questions(questions)
            .inscription(this)
            .event(event).build();
    }
}
