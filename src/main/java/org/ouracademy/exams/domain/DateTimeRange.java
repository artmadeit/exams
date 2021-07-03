package org.ouracademy.exams.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DateTimeRange {
    LocalDateTime inicio;
    LocalDateTime fin;

    public static final LocalDateTime INFINITY = null;
}
