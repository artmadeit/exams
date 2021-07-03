package org.ouracademy.exams.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class ExamSpecification {
    String descripcion;
    
    @Embedded
    DateTimeRange rango;
}
