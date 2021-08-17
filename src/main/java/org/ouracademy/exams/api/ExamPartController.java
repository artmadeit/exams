package org.ouracademy.exams.api;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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

    public static class SectionRequest extends CreateExamRequest { }

    public static class TextRequest extends CreateExamRequest {
        @NotBlank
        @Override
        public String getDescription() { return this.description; }
    }

    @PostMapping("/{parentId}/section")
    public ExamPartResponse createSection(@PathVariable Long parentId, @Valid @RequestBody SectionRequest request) {
        var parent = getParent(parentId);
        var examPart = ExamPart.section(request.getTitle(), request.getDescription());
        parent.addChild(examPart);
        return new ExamPartResponse(repository.save(examPart));
    }

    @PostMapping("/{parentId}/text")
    public ExamPartResponse createText(@PathVariable Long parentId, @Valid @RequestBody TextRequest request) {
        var parent = getParent(parentId);
        var examPart = ExamPart.text(request.getTitle(), request.getDescription());
        parent.addChild(examPart);
        return new ExamPartResponse(repository.save(examPart));
    }

    @Transactional
    @PutMapping("/section/{id}")
    public ExamPartResponse updateSection(@PathVariable Long id, @Valid @RequestBody SectionRequest request) {
       return updateExamPart(id, request);
    }

    @Transactional
    @PutMapping("/text/{id}")
    public ExamPartResponse updateText(@PathVariable Long id, @Valid @RequestBody TextRequest request) {
        return updateExamPart(id, request);
    }

    public ExamPartResponse updateExamPart(Long id, CreateExamRequest request){
        var examPart = repository.findById(id).orElseThrow();
        examPart.setContent(request.getDescription());
        examPart.setTitle(request.getTitle());
        return new ExamPartResponse(examPart);
    }

    
    @Getter @Setter
    public static class QuestionRequest {
        @NotBlank
        String description;
    }

    @PostMapping("/{parentId}/question")
    public ExamPartResponse createQuestion(@PathVariable Long parentId, @Valid @RequestBody QuestionRequest request) {
        var parent = getParent(parentId);
        var examPart = new Question(request.getDescription(), parent);
        return new ExamPartResponse(repository.save(examPart));
    }

    @Transactional
    @PutMapping("/question/{id}")
    public ExamPartResponse updateQuestion(@PathVariable Long id, @Valid @RequestBody QuestionRequest request) {
        var question = repository.findById(id).orElseThrow();
        question.setContent(request.getDescription());
        return new ExamPartResponse(question);
    }
    
    private ExamPart getParent(Long parentId) {
        return repository.findById(parentId).orElseThrow(() -> new NotFoundException("exam_part_parent", Map.of("parentId", parentId)));
    }

    @PostMapping("/{parentId}/alternative")
    public ExamPartResponse createAlternative(@PathVariable Long parentId, @Valid @RequestBody QuestionRequest request) {
        var alternative = Question.alternative(request.getDescription(), (Question) getParent(parentId));
        return new ExamPartResponse(repository.save(alternative));
    }

    @Transactional
    @PutMapping("/alternative/{id}")
    public ExamPartResponse updateAlternative(@PathVariable Long id, @Valid @RequestBody QuestionRequest request) {
        var alternative = repository.findById(id).orElseThrow();
        alternative.setContent(request.getDescription());
        return new ExamPartResponse(alternative);
    }
}
