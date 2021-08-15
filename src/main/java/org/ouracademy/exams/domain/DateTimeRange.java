package org.ouracademy.exams.domain;

import java.time.LocalDateTime;
import java.util.Map;

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
        this.start = start;
        this.end = end;
        
        this.validate();
    }

    private void validate() {
        if(!start.isBefore(end()))
            throw new BadArgumentsException("date_time_range.start_greater_end", Map.of("start", start, "end", end));
    }

    private LocalDateTime end() {
        // self encapsulation
        return end != null? end: LocalDateTime.MAX;
    }

    public boolean hasStarted() {            
        // t: start---------now-------
    
        // t: --start ------
        //    --now --------
        var now = LocalDateTime.now();
        return start.isBefore(now) || start.isEqual(now);
    }

    public boolean hasEnded() {
        // t: end---------now-------
        return end().isBefore(LocalDateTime.now());
    }
}
