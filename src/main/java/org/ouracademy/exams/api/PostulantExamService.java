package org.ouracademy.exams.api;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.ouracademy.exams.domain.BuildExamPartSpecification;
import org.ouracademy.exams.domain.ExamRandomBuilder;
import org.ouracademy.exams.domain.ExamTestData;
import org.ouracademy.exams.domain.PostulantExam;
import org.ouracademy.exams.domain.PostulantQuestion;
import org.ouracademy.exams.event.ExamEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import static org.ouracademy.exams.domain.BuildExamPartSpecification.createExamSpecification;
import static org.ouracademy.exams.domain.BuildExamPartSpecification.with;

import org.ouracademy.exams.domain.ExamPart.Type;

@Service
@Transactional
@AllArgsConstructor
public class PostulantExamService {

    PostulantRepository postulantRepository;
    ExamEventRepository examEventRepository;
    PostulantExamRepository postulantExamRepository;
    ExamPartContainerRepository examPartContainerRepository;

    public PostulantExam finish(Long id) {
        var postulantExam = postulantExamRepository.findById(id).orElseThrow();
        postulantExam.finish();
        
        return postulantExam;
    }

    @Data
    public static class StartExamRequest {
        @NotNull Long postulantId;
        @NotNull Long eventExamId;
    }


    public PostulantExamResponse start(StartExamRequest request) {
        var postulant = postulantRepository.findById(request.getPostulantId())
            .orElseThrow(() -> new NoSuchElementException("Not found postulant with id:" + request.getPostulantId()));
        var examEvent = examEventRepository.findById(request.getEventExamId())
            .orElseThrow(() -> new NoSuchElementException("Not found examEvent with id:" + request.getEventExamId()));
        
        var postulantExam = postulant.start(examEvent, randomQuestions());
        postulantExamRepository.save(postulantExam);
        return new PostulantExamResponse(postulantExam);
    }


    @Getter
    public static class PostulantExamResponse {
        Long examPostulantId;
        List<Long> questionIds;

        PostulantExamResponse(PostulantExam postulantExam) {
            this.examPostulantId = postulantExam.getId();
            this.questionIds = postulantExam.getQuestions().stream()
                .map(PostulantQuestion::getId)
                .collect(Collectors.toList());
        }
    }

    private List<PostulantQuestion> randomQuestions() {
        return new ExamRandomBuilder().from(
            // TODO: get unmsm exam templates
            examPartContainerRepository.findByTitleIn(List.of("Examen 1", "Examen 2")), getUnmsmSpec()
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
}
