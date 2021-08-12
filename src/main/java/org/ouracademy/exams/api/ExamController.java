package org.ouracademy.exams.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.ouracademy.exams.domain.structure.ExamPart;
import org.ouracademy.exams.domain.structure.ExamPartInfoResponse;
import org.ouracademy.exams.domain.structure.ExamRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@RestController
@RequestMapping("/exam")
@AllArgsConstructor
@Secured({ "ROLE_ADMIN" })
public class ExamController {
    ExamRepository repository;


    @Getter
    @AllArgsConstructor
    public static class ExamPartResponse2 {
        Long id;
        Long parentId;
        String title;
        String content;
        ExamPart.Type type;
        List<ExamPartResponse2> childs;

        public static ExamPartResponse2 recursive(ExamPart exam) {
            var childs = exam.getChilds().stream().map(ExamPartResponse2::recursive).collect(Collectors.toList());
            var parentId = exam.getParent() != null ?  exam.getParent().getId() : null;
            return new ExamPartResponse2(exam.getId(), parentId ,exam.getTitle(), exam.getContent(), exam.getType(), childs);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamPartResponse2> findById(@PathVariable Long id) {
        return ResponseEntity.of(
            repository.findById(id).map(ExamPartResponse2::recursive)
        );
    }

    @GetMapping
    public Page<ExamPartInfoResponse> getAll(final Pageable pageable) {
        return repository.findAll(pageable).map(ExamPartInfoResponse::fromExam);
    }

    @Getter @Setter
    public static class CreateExamRequest {
        @NotBlank
        String title;
        String description;
    }

    @PostMapping
    public ExamPartResponse createExam(@Valid @RequestBody CreateExamRequest request) {
        var examPart = ExamPart.exam(request.getTitle(), request.getDescription());
        return new ExamPartResponse(repository.save(examPart));
    }
}
