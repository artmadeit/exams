package org.ouracademy.exams.api;

import org.ouracademy.exams.domain.postulant.Postulant;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postulant")
public class PostulantController {

    @GetMapping("/me")
    public Postulant currentPostulant(@AuthenticationPrincipal Postulant postulant) {
        return postulant;
    }
}
