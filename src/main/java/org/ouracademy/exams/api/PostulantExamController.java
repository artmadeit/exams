package org.ouracademy.exams.api;

import org.ouracademy.exams.domain.PostulantExamService;

import javax.annotation.security.RolesAllowed;

import org.ouracademy.exams.domain.PostulantExamService.PostulantExamResponse;
import org.ouracademy.exams.domain.postulant.Postulant;
import org.ouracademy.exams.domain.PostulantExamService.PostulantExamResultResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/postulant-exam")
public class PostulantExamController {

    PostulantExamService postulantExamService;

    @RolesAllowed("POSTULANT")
    @PostMapping("/start-or-get/{eventExamId}")
    public PostulantExamResponse getOrStart(@PathVariable Long eventExamId, @AuthenticationPrincipal Postulant postulant) {
        var exam = postulantExamService.getOrStart(eventExamId, postulant);
        return new PostulantExamResponse(exam);
    }

    @RolesAllowed("POSTULANT")
    @PreAuthorize("@postulantExamService.isTaker(principal, #id)")
    @PostMapping("/finish/{id}")
    @Transactional
    public PostulantExamResponse finish(@PathVariable Long id) {
        return postulantExamService.finish(id);
    }

    @GetMapping("/{id}")
    @Transactional
    public PostulantExamResultResponse getById(@PathVariable Long id) {
        //TODO: validate that exam time is ended
        return postulantExamService.getById(id);
    }
}
