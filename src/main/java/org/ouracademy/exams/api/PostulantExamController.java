package org.ouracademy.exams.api;

import static org.ouracademy.exams.domain.BuildExamPartSpecification.createExamSpecification;
import static org.ouracademy.exams.domain.BuildExamPartSpecification.with;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.ouracademy.exams.domain.BuildExamPartSpecification;
import org.ouracademy.exams.domain.ExamPart.Type;
import org.ouracademy.exams.domain.ExamRandomBuilder;
import org.ouracademy.exams.domain.PostulantExam;
import org.ouracademy.exams.domain.PostulantQuestion;
import org.ouracademy.exams.event.ExamEventRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@RestController
@RequestMapping("/postulant-exam")
public class PostulantExamController {

    PostulantRepository postulantRepository;
    ExamEventRepository examEventRepository;
    PostulantExamRepository postulantExamRepository;

    PostulantExamService postulantExamService;

    @Data
    public static class StartExamRequest {
        @NotNull Long postulantId;
        @NotNull Long eventExamId;
    }

    @PostMapping("/start")
    public PostulantExam start(@Valid StartExamRequest request) {
        var postulant = postulantRepository.findById(request.getPostulantId())
            .orElseThrow(() -> new NoSuchElementException("Not found postulant with id:" + request.getPostulantId()));
        var examEvent = examEventRepository.findById(request.getEventExamId())
            .orElseThrow(() -> new NoSuchElementException("Not found examEvent with id:" + request.getEventExamId()));
        
        var postulantExam = postulant.start(examEvent, randomQuestions());
        postulantExamRepository.save(postulantExam);
        return postulantExam;
    }

    

    private List<PostulantQuestion> randomQuestions() {
        return new ExamRandomBuilder().from(
            // TODO: get unmsm exam templates
            List.of(), getUnmsmSpec()
        );
    }


    private BuildExamPartSpecification getUnmsmSpec() {
        return createExamSpecification(
            List.of(
                with(1, Type.SECTION).title("CAPACIDADES COMUNICATIVAS")
                    .addChild(with(2, Type.TEXT).addChild(with(5, Type.QUESTION))),
                with(1, Type.SECTION).title("CAPACIDADES LOGICO MATEMATICAS")
                    .addChild(with(5, Type.QUESTION)),
                with(1, Type.SECTION).title("CAPACIDADES INVESTIGATIVAS")
                    .addChild(with(5, Type.QUESTION)),
                with(1, Type.SECTION).title("PENSAMIENTO CRITICO")
                    .addChild(with(5, Type.QUESTION))
            ));
    }

    @PostMapping("/finish/{id}")
    public PostulantExam finish(@PathVariable Long id) {
        return postulantExamService.finish(id);
    }
}
