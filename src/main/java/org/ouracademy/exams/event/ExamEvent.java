package org.ouracademy.exams.event;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.ouracademy.exams.domain.DateTimeRange;

import lombok.Builder;

@Entity
public class ExamEvent {

    @Id
    Long id;

    String description;
    
    @Embedded
    DateTimeRange range;

    @Builder
    ExamEvent(String description, DateTimeRange range) {
        this.description = description;
        this.range = range;
    }

    /**
     * @apiNote jpa only
     */
    ExamEvent() {}
}
