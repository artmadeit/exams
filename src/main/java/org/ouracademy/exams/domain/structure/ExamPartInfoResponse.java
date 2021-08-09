package org.ouracademy.exams.domain.structure;

import java.time.LocalDateTime;

import lombok.Value;

@Value
public class ExamPartInfoResponse {
    Long id;
    String title;
    String content;
    LocalDateTime lastModifiedDate;

    public static ExamPartInfoResponse fromExam(Exam exam) {
        return new ExamPartInfoResponse(exam.id, exam.title, exam.content, exam.lastModifiedDate);
    }
}
