package org.ouracademy.exams.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.ouracademy.exams.domain.structure.ExamPart;
import org.ouracademy.exams.domain.structure.ExamPartRepository;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@AllArgsConstructor
public class ExamPartController {
    
    ExamPartRepository repository;

    @Data
    public static class CreateExamRequest {
        @NotNull
        String title;
        @Nullable
        String description;
    }

    @PostMapping("/exam")
    public ExamPart createExam(@Valid @RequestBody CreateExamRequest request) {
        var examPart = ExamPart.exam(request.getTitle(), request.getDescription());
        return repository.save(examPart);
    }
}
