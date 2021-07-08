package org.ouracademy.exams.event;

import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.ouracademy.exams.domain.DateTimeRange;
import org.ouracademy.exams.utils.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@RestController
@RequestMapping("/exam-event")
public class ExamEventController {
    ExamEventRepository repository;

    @GetMapping("/{id}")
    public ExamEvent find(@PathVariable Long id) {
        return this.repository.findById(id).orElseThrow(() -> new NotFoundException(ExamEvent.class, id));
    }

    @Data
    public static class ExamEventRequest {
        String description;
        @NotNull
        LocalDateTime start;
        @NotNull
        LocalDateTime end;
    }

    @PostMapping
    public ExamEvent create(@RequestBody @Valid ExamEventRequest request) {
        var examEvent = ExamEvent.builder()
            .description(request.description)
            .range(new DateTimeRange(request.start, request.end))
            .build();
        return this.repository.save(examEvent);
    }
}
