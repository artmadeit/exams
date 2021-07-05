package org.ouracademy.exams.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DateTimeRange {
    LocalDateTime start;
    LocalDateTime end;
}
