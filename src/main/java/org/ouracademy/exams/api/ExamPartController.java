package org.ouracademy.exams.api;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.ouracademy.exams.api.ExamController.CreateExamRequest;
import org.ouracademy.exams.domain.structure.ExamPart;
import org.ouracademy.exams.domain.structure.ExamPartRepository;
import org.ouracademy.exams.domain.structure.Question;
import org.ouracademy.exams.utils.NotFoundException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@RestController
@AllArgsConstructor
@Secured({ "ROLE_ADMIN" })
public class ExamPartController {
    
    ExamPartRepository repository;

    @Getter @Setter
    public static class CreateSectionRequest extends CreateExamRequest {
        @NotNull
        Long parentId;        
    }

    @Getter @Setter
    public static class CreateTextRequest extends CreateSectionRequest{
        @NotBlank
        String description;
    }

    @PostMapping("/section")
    public ExamPartResponse createSection(@Valid @RequestBody CreateSectionRequest request) {
        var parent = getParent(request.getParentId());
        var examPart = ExamPart.section(request.getTitle(), request.getDescription(), parent);
        return new ExamPartResponse(repository.save(examPart));
    }

    @Transactional
    @PutMapping("/section/{id}")
    public ExamPartResponse updateSection(@PathVariable("id") Optional<ExamPart> examPartOptional, @Valid @RequestBody CreateSectionRequest request) {
       return updateExamPart(examPartOptional, request);
    }

    @Transactional
    @PutMapping("/text/{id}")
    public ExamPartResponse updateText(@PathVariable("id") Optional<ExamPart> examPartOptional, @Valid @RequestBody CreateTextRequest request) {
        return updateExamPart(examPartOptional, request);
    }

    @PostMapping("/text")
    public ExamPartResponse createText(@Valid @RequestBody CreateSectionRequest request) {
        var parent = getParent(request.getParentId());
        var examPart = ExamPart.text(request.getTitle(), request.getDescription(), parent);
        return new ExamPartResponse(repository.save(examPart));
    }

    public ExamPartResponse updateExamPart(Optional<ExamPart> examPartOptional, CreateSectionRequest request){
        var examPart = examPartOptional.orElseThrow();
        examPart.setContent(request.getDescription());
        examPart.setTitle(request.getTitle());
        examPart.setParent(getParent(request.getParentId()));
        return new ExamPartResponse(examPart);
    }

    
    @Getter @Setter
    public static class CreateQuestionRequest {
        @NotBlank
        String description;
        @NotNull
        Long parentId;
    }

    @PostMapping("/question")
    public ExamPartResponse createQuestion(@Valid @RequestBody CreateQuestionRequest request) {
        var parent = getParent(request.getParentId());
        var examPart = new Question(request.getDescription(), parent);
        return new ExamPartResponse(repository.save(examPart));
    }

    @PostMapping("/alternative")
    public ExamPartResponse createAlternative(@Valid @RequestBody CreateQuestionRequest request) {
        var alternative = Question.alternative(request.getDescription(), (Question) getParent(request.getParentId()));
        return new ExamPartResponse(repository.save(alternative));
    }

    @Transactional
    @PutMapping("/alternative")
    public ExamPartResponse updateAlternative(@PathVariable("id") Optional<ExamPart> alternativeOptional, @Valid @RequestBody CreateQuestionRequest request) {
        var alternative = alternativeOptional.orElseThrow();
        alternative.setContent(request.getDescription());
        return new ExamPartResponse(alternative);
    }

    @Transactional
    @PutMapping("/question")
    public ExamPartResponse updateQuestion(@PathVariable("id") Optional<Question> questionOptional, @Valid @RequestBody CreateQuestionRequest request) {
        var question = questionOptional.orElseThrow();
        question.setContent(request.getDescription());
        question.setParent(getParent(request.getParentId()));
        return new ExamPartResponse(question);
    }
    
    private ExamPart getParent(Long parentId) {
        return repository.findById(parentId).orElseThrow(() -> new NotFoundException("exam_part_parent", new Object[] { parentId }));
    }
}
