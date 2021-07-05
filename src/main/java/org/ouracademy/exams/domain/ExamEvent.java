package org.ouracademy.exams.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

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
}
