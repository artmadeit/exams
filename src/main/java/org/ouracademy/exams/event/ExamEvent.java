package org.ouracademy.exams.event;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.ouracademy.exams.domain.DateTimeRange;

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
}
