package org.ouracademy.exams.api;
import lombok.Getter;
import org.ouracademy.exams.domain.structure.ExamPart;

@Getter
public static class ExamPartResponse {

    Long id;
    String content;

    ExamPartResponse(ExamPart examPart) {
        this.id = examPart.getId();
        this.content = examPart.getContent();
    }

}