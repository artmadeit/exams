package org.ouracademy.exams.api;

import java.security.Principal;

import org.ouracademy.exams.auth.UserAccount;
import org.ouracademy.exams.domain.Postulant;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/postulant")
@AllArgsConstructor
public class PostulantController {
    PostulantRepository repository;

    @GetMapping("/me")
    public Postulant currentPostulant(@AuthenticationPrincipal Postulant postulant) {
        return postulant;
    }
}
