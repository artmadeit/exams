package org.ouracademy.exams.api;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.ouracademy.exams.domain.DateTimeRange;
import org.ouracademy.exams.domain.event.ExamEvent;
import org.ouracademy.exams.domain.event.ExamEventRepository;
import org.ouracademy.exams.utils.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@RestController
@RequestMapping("/exam-event")
public class ExamEventController {
    ExamEventRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<ExamEvent> find(@PathVariable("id") Optional<ExamEvent> event) {
        return ResponseEntity.of(event);
    }

    @Data
    public static class ExamEventRequest {
        @NotBlank
        String title;
        String description;
        @NotNull
        LocalDateTime start;
        @NotNull
        LocalDateTime end;
    }

    @RolesAllowed("ADMIN")
    @PostMapping
    public ExamEvent create(@RequestBody @Valid ExamEventRequest request) {
        var examEvent = ExamEvent.builder()
            .title(request.title)
            .description(request.description)
            .range(new DateTimeRange(request.start, request.end))
            .build();
        return this.repository.save(examEvent);
    }

    @GetMapping("")
    public Page<ExamEvent> getAll(final Pageable pageable) {
        return repository.findAll(pageable);
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/{id}")
    @Transactional
    public ExamEvent edit(@PathVariable Long id, @RequestBody @Valid ExamEventRequest request) {
        var examEvent = this.repository.findById(id).orElseThrow(() -> new NotFoundException(ExamEvent.class));
        examEvent.setTitle(request.title);
        examEvent.setDescription(request.description);
        examEvent.setRange(new DateTimeRange(request.start, request.end));
        return examEvent;
    }
}
