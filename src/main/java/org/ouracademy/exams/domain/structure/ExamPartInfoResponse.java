package org.ouracademy.exams.domain.structure;

import java.time.LocalDateTime;

import lombok.Value;

@Value
public class ExamPartInfoResponse {
    Long id;
    String title;
    String content;
    LocalDateTime lastModifiedDate;
}
