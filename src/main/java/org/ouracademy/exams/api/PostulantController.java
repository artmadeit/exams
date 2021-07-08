package org.ouracademy.exams.api;

import org.ouracademy.exams.domain.Postulant;
import org.ouracademy.exams.utils.NotFoundException;
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
    public Postulant find(@PathVariable Long id) {
        return this.repository.findById(id).orElseThrow(() -> new NotFoundException(Postulant.class, id));
    }
}
