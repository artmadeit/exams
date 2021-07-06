package org.ouracademy.exams.api;

import java.util.Optional;

import org.ouracademy.exams.domain.ExamEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/exam-event")
public class ExamEventController {
    ExamEventRepository repository;

    @GetMapping("/{id}")
    public Optional<ExamEvent> find(@PathVariable Long id) {
        return this.repository.findById(id);
    }
}
