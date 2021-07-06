package org.ouracademy.exams.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;

@Embeddable
@AllArgsConstructor
public class DateTimeRange {
    @Column(name = "date_time_start")
    LocalDateTime start;
    @Column(name = "date_time_end")
    LocalDateTime end;

    /**
     * @apiNote jpa only
     */
    DateTimeRange() {}
}
