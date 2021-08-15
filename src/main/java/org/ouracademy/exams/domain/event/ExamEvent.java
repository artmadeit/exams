package org.ouracademy.exams.domain.event;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.ouracademy.exams.domain.DateTimeRange;
import org.ouracademy.exams.utils.BadArgumentsException;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity

@EntityListeners(AuditingEntityListener.class)
public class ExamEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter String title;
    @Setter String description;
    
    @Embedded
    @Setter
    public DateTimeRange range;

    @CreatedDate
    public LocalDateTime createdDate;

    @LastModifiedDate
    public LocalDateTime lastModifiedDate;


    @Builder
    ExamEvent(String title, String description, DateTimeRange range) {
        this.title = title;
        this.description = description;
        this.range = range;
    }

    /**
     * @apiNote jpa only
     */
    ExamEvent() {}


    public static class NotStartedException extends BadArgumentsException {
        private static final URI ERROR_TYPE = URI.create("https://our-academy.org/start-exam-not-started");

        public NotStartedException(ExamEvent event) {
            super("exam_event.not_started", "Exam not started", ERROR_TYPE, Map.of("start", event.getRange().getStart()));
        }
    }

    public static class EndedException extends BadArgumentsException {
        private static final URI ERROR_TYPE = URI.create("https://our-academy.org/start-exam-ended");

        public EndedException(ExamEvent event) {
            super("exam_event.ended", "Exam ended", ERROR_TYPE, Map.of("end", event.getRange().getEnd()));
        }
    }

    public boolean hasStarted() {
        return this.getRange().hasStarted();
    }

    public boolean hasEnded() {
        return this.getRange().hasEnded();
    }
}
