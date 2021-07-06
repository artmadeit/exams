package org.ouracademy.exams.api;

import java.util.Optional;

import org.ouracademy.exams.domain.Postulant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/postulant")
@AllArgsConstructor
public class PostulantController {
    PostulantRepository repository;

    @GetMapping("/{id}")
    public Optional<Postulant> find(@PathVariable Long id) {
        return this.repository.findById(id);
    }
}
