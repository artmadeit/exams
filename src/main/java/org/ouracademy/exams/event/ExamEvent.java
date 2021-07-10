package org.ouracademy.exams.event;

import java.net.URI;
import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.ouracademy.exams.domain.DateTimeRange;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class ExamEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter String description;
    
    @Embedded
    @Setter DateTimeRange range;

    @Builder
    ExamEvent(String description, DateTimeRange range) {
        this.description = description;
        this.range = range;
    }

    /**
     * @apiNote jpa only
     */
    ExamEvent() {}


    public static class NotStartedException extends AbstractThrowableProblem {
        private static final URI TYPE = URI.create("https://our-academy.org/start-exam-not-started");

        public NotStartedException(ExamEvent event) {
            super(TYPE, "Exam not started", Status.BAD_REQUEST, "Exam will start at:" + event.range.getStart());
        }
    }

    public static class EndedException extends AbstractThrowableProblem {
        private static final URI TYPE = URI.create("https://our-academy.org/start-exam-ended");

        public EndedException(ExamEvent event) {
            super(TYPE, "Exam ended", Status.BAD_REQUEST, "Exam has ended at:" + event.range.getEnd());
        }
    }

    public boolean hasStarted() {            
        // t: start---------now-------

        // t: --start ------
        //    --now --------
        var now = LocalDateTime.now();
        return this.range.getStart().isBefore(now) || this.range.getStart().isEqual(now);
    }

    public boolean hasEnded() {
        // t: end---------now-------
        return this.range.getEnd().isBefore(LocalDateTime.now());
    }
}
