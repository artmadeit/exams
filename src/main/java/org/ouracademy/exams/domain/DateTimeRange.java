package org.ouracademy.exams.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.ouracademy.exams.utils.BadArgumentsException;

import lombok.Getter;

@Getter
@Embeddable
public class DateTimeRange {
    @Column(name = "date_time_start")
    LocalDateTime start;
    @Column(name = "date_time_end")
    LocalDateTime end;

    /**
     * @apiNote jpa only
     */
    DateTimeRange() {}

    public DateTimeRange(LocalDateTime start, LocalDateTime end) {
        validate(start, end);
            
        this.start = start;
        this.end = end;
    }

    private void validate(LocalDateTime start, LocalDateTime end) {
        var endX = end != null? end: LocalDateTime.MAX;
        if(!start.isBefore(endX))
            throw new BadArgumentsException("start < end, start:" + start + ", end:" + end);
    }
}
