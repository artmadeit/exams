package org.ouracademy.exams.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.ouracademy.exams.domain.structure.ExamPart;
import org.ouracademy.exams.domain.structure.ExamPartRepository;
import org.ouracademy.exams.domain.structure.Question;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@RestController
@AllArgsConstructor
public class ExamPartController {
    
    ExamPartRepository repository;

    @Getter @Setter
    public static class CreateExamRequest {
        @NotNull
        String title;
        String description;
    }

    @PostMapping("/exam")
    public ExamPart createExam(@Valid @RequestBody CreateExamRequest request) {
        var examPart = ExamPart.exam(request.getTitle(), request.getDescription());
        return repository.save(examPart);
    }

    @Getter @Setter
    public static class CreateSectionRequest extends CreateExamRequest {
        @NotNull
        Long parentId;        
    }

    @PostMapping("/section")
    public ExamPart createSection(@Valid @RequestBody CreateSectionRequest request) {
        var parent = repository.findById(request.getParentId()).orElseThrow();
        var examPart = ExamPart.section(request.getTitle(), request.getDescription(), parent);
        return repository.save(examPart);
    }

    @PostMapping("/text")
    public ExamPart createText(@Valid @RequestBody CreateSectionRequest request) {
        var parent = repository.findById(request.getParentId()).orElseThrow();
        var examPart = ExamPart.text(request.getTitle(), request.getDescription(), parent);
        return repository.save(examPart);
    }

    
    @Getter @Setter
    public static class CreateQuestionRequest {
        @NotNull
        String description;
        @NotNull
        Long parentId;
    }

    @PostMapping("/question")
    public ExamPart createQuestion(@Valid @RequestBody CreateQuestionRequest request) {
        var parent = repository.findById(request.getParentId()).orElseThrow();
        var examPart = new Question(request.getDescription(), parent);
        return repository.save(examPart);
    }
}
