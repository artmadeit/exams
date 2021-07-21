package org.ouracademy.exams.api;

import org.ouracademy.exams.domain.PostulantExamService;
import org.ouracademy.exams.domain.PostulantExamService.PostulantExamResponse;
import org.ouracademy.exams.domain.postulant.Postulant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/postulant-exam")
public class PostulantExamController {

    PostulantExamService postulantExamService;


    @PostMapping("/start/{eventExamId}")
    public PostulantExamResponse start(@PathVariable Long eventExamId, @AuthenticationPrincipal Postulant postulant) {
        return postulantExamService.start(eventExamId, postulant);
    }

    @PreAuthorize("@postulantExamService.isTaker(authentication, #id)")
    @PostMapping("/finish/{id}")
    public PostulantExamResponse finish(@PathVariable Long id) {
        return postulantExamService.finish(id);
    }
}
