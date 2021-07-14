package org.ouracademy.exams.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DateTimeRangeTests {
    @Test
    public void test_has_started() {
        
        assertTrue(new DateTimeRange(
            LocalDateTime.of(2021, 6, 2, 10, 20),
            LocalDateTime.of(2050, 6, 2, 10, 20)
        ).hasStarted());

        assertTrue(new DateTimeRange(
            LocalDateTime.now(),
            LocalDateTime.of(2050, 6, 2, 10, 20)
        ).hasStarted());
    }

    @Test
    public void test_has_ended() {
        
        assertTrue(new DateTimeRange(
            LocalDateTime.of(2021, 6, 2, 10, 20),
            LocalDateTime.of(2021, 6, 2, 14, 20)
        ).hasEnded());

        assertFalse(new DateTimeRange(
            LocalDateTime.now(),
            null
        ).hasEnded());
    }
}
