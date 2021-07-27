package org.ouracademy.exams.domain.event;

import java.net.URI;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.ouracademy.exams.domain.DateTimeRange;
import org.ouracademy.exams.utils.OuracademyException;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

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
    @Setter
    public DateTimeRange range;

    @Builder
    ExamEvent(String description, DateTimeRange range) {
        this.description = description;
        this.range = range;
    }

    /**
     * @apiNote jpa only
     */
    ExamEvent() {}


    public static class NotStartedException extends OuracademyException {
        private static final URI ERROR_TYPE = URI.create("https://our-academy.org/start-exam-not-started");

        public NotStartedException(ExamEvent event) {
            super("exam_event.not_started", "Exam not started", ERROR_TYPE, new Object[] {event.range.getStart()});
        }

        @Override
        public StatusType getStatus() {
            return Status.BAD_REQUEST;
        }
    }

    public static class EndedException extends OuracademyException {
        private static final URI ERROR_TYPE = URI.create("https://our-academy.org/start-exam-ended");

        public EndedException(ExamEvent event) {
            super("exam_event.ended", "Exam ended", ERROR_TYPE, new Object[] { event.range.getEnd() });
        }

        @Override
        public StatusType getStatus() {
            return Status.BAD_REQUEST;
        }
    }

    public boolean hasStarted() {
        return this.getRange().hasStarted();
    }

    public boolean hasEnded() {
        return this.getRange().hasEnded();
    }
}
