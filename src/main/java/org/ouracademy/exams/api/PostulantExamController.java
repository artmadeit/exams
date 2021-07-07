package org.ouracademy.exams.api;

import javax.validation.Valid;

import org.ouracademy.exams.api.PostulantExamService.StartExamRequest;
import org.ouracademy.exams.domain.PostulantExam;
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


    @PostMapping("/start")
    public PostulantExam start(@Valid StartExamRequest request) {
        return postulantExamService.start(request);
    }

    @PostMapping("/finish/{id}")
    public PostulantExam finish(@PathVariable Long id) {
        return postulantExamService.finish(id);
    }
}
