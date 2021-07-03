package org.ouracademy.exams.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;

@Entity
public class ExamSpecification {

    @Id
    Long id;

    String description;
    
    @Embedded
    DateTimeRange range;

    @Builder
    ExamSpecification(String description, DateTimeRange range) {
        this.description = description;
        this.range = range;
    }
}
