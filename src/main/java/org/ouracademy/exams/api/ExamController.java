package org.ouracademy.exams.api;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.ouracademy.exams.domain.structure.ExamPart;
import org.ouracademy.exams.domain.structure.ExamPartInfoResponse;
import org.ouracademy.exams.domain.structure.ExamRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@RestController
@AllArgsConstructor
public class ExamController {
    ExamRepository repository;

    @GetMapping("/exams")
    public Page<ExamPartInfoResponse> getAll(final Pageable pageable) {
        return repository.findAll(pageable).map(ExamPartInfoResponse::fromExam);
    }

    @Getter @Setter
    public static class CreateExamRequest {
        @NotBlank
        String title;
        String description;
    }

    @PostMapping("/exam")
    public ExamPartResponse createExam(@Valid @RequestBody CreateExamRequest request) {
        var examPart = ExamPart.exam(request.getTitle(), request.getDescription());
        return new ExamPartResponse(repository.save(examPart));
    }
}
