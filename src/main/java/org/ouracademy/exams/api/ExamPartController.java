package org.ouracademy.exams.api;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.ouracademy.exams.domain.structure.ExamPart;
import org.ouracademy.exams.domain.structure.ExamPartRepository;
import org.ouracademy.exams.domain.structure.Question;
import org.ouracademy.exams.utils.NotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    public ExamPartResponse createExam(@Valid @RequestBody CreateExamRequest request) {
        var examPart = ExamPart.exam(request.getTitle(), request.getDescription());
        return new ExamPartResponse(repository.save(examPart));
    }

    @GetMapping("/exams")
    public Page<ExamPartResponse> getAll(final Pageable pageable) {
        return repository.findAll(pageable).map(ExamPartResponse::new);
    }


   

    @Getter @Setter
    public static class CreateSectionRequest extends CreateExamRequest {
        @NotNull
        Long parentId;        
    }

    @Getter @Setter
    public static class CreateTextRequest extends CreateSectionRequest{
        @NotNull
        String description;
     
    }

    @PostMapping("/section")
    public ExamPart createSection(@Valid @RequestBody CreateSectionRequest request) {
        var parent = getParent(request.getParentId());
        var examPart = ExamPart.section(request.getTitle(), request.getDescription(), parent);
        return repository.save(examPart);
    }

    @Transactional
    @PutMapping("/section/{id}")
    public ExamPart updateSection(@PathVariable("id") Optional<ExamPart> examPartOptional, @Valid @RequestBody CreateSectionRequest request) {
       return updateExamPart(examPartOptional, request);
    }

    @Transactional
    @PutMapping("/text/{id}")
    public ExamPart updateText(@PathVariable("id") Optional<ExamPart> examPartOptional, @Valid @RequestBody CreateTextRequest request) {
        return updateExamPart(examPartOptional, request);
    }

    @PostMapping("/text")
    public ExamPart createText(@Valid @RequestBody CreateSectionRequest request) {
        var parent = getParent(request.getParentId());
        var examPart = ExamPart.text(request.getTitle(), request.getDescription(), parent);
        return repository.save(examPart);
    }

    public ExamPart updateExamPart(Optional<ExamPart> examPartOptional, CreateSectionRequest request){
        var examPart = examPartOptional.orElseThrow();
        examPart.setContent(request.description);
        examPart.setTitle(request.title);
        examPart.setParent(getParent(request.getParentId()));
        return examPart;
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
        var parent = getParent(request.getParentId());
        var examPart = new Question(request.getDescription(), parent);
        return repository.save(examPart);
    }

    @PostMapping("/alternative")
    public ExamPart createAlternative(@Valid @RequestBody CreateQuestionRequest request) {
        var alternative = Question.alternative(request.getDescription(), (Question) getParent(request.getParentId()));
        return repository.save(alternative);
    }

    @Transactional
    @PutMapping("/question")
    public ExamPart updateQuestion(@PathVariable("id") Optional<Question> questionOptional, @Valid @RequestBody CreateQuestionRequest request) {
        var question = questionOptional.orElseThrow();
        question.setContent(request.getDescription());
        question.setParent(getParent(request.getParentId()));
        return question;
    }
    
    private ExamPart getParent(Long parentId) {
        return repository.findById(parentId).orElseThrow(() -> new NotFoundException("exam_part_parent", new Object[] { parentId }));
    }
}
