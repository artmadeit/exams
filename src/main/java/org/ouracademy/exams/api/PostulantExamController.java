package org.ouracademy.exams.api;

import java.util.Optional;

import org.ouracademy.exams.domain.PostulantExam;
import org.ouracademy.exams.domain.PostulantExamService;
import org.ouracademy.exams.domain.PostulantExamService.PostulantExamResponse;
import org.ouracademy.exams.domain.event.ExamEvent;
import org.ouracademy.exams.domain.postulant.Postulant;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
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

    @PostMapping("/start-or-get/{eventExamId}")
    public ResponseEntity<PostulantExamResponse> startOrGet(@PathVariable("eventExamId") Optional<ExamEvent> optionalExamEvent, @AuthenticationPrincipal Postulant postulant) {
        return ResponseEntity.of(
            optionalExamEvent.map(examEvent -> postulantExamService.startOrGet(examEvent, postulant))
        );
    }

    @PreAuthorize("@postulantExamService.isTaker(principal, #id)")
    @PostMapping("/finish/{id}")
    @Transactional
    public ResponseEntity<PostulantExamResponse> finish(@PathVariable("id") Optional<PostulantExam> optionalExam) {
        return ResponseEntity.of(
            optionalExam.map(postulantExam -> {
                postulantExam.finish();
                return new PostulantExamResponse(postulantExam);
            })
        );
    }
}
