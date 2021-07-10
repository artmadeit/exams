package org.ouracademy.exams.domain;

import static org.ouracademy.exams.domain.build.BuildExamPartSpecification.createExamSpecification;
import static org.ouracademy.exams.domain.build.BuildExamPartSpecification.with;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.ouracademy.exams.domain.build.BuildExamPartSpecification;
import org.ouracademy.exams.domain.build.ExamRandomBuilder;
import org.ouracademy.exams.domain.event.ExamEvent;
import org.ouracademy.exams.domain.event.ExamEventRepository;
import org.ouracademy.exams.domain.postulant.Postulant;
import org.ouracademy.exams.domain.structure.ExamPart.Type;
import org.ouracademy.exams.domain.structure.ExamPartRepository;
import org.ouracademy.exams.utils.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@Transactional
@AllArgsConstructor
public class PostulantExamService {

    ExamEventRepository examEventRepository;
    PostulantExamRepository postulantExamRepository;
    ExamPartRepository examPartRepository;

    public PostulantExamResponse finish(Long id) {
        var postulantExam = postulantExamRepository.findById(id).orElseThrow();
        postulantExam.finish();
        
        return new PostulantExamResponse(postulantExam);
    }

    public static class ExamAlreadyStartedException extends AbstractThrowableProblem {
        private static final URI TYPE = URI.create("https://our-academy.org/start-exam-already-started");

        public ExamAlreadyStartedException() {
            super(TYPE, "Exam already started", Status.BAD_REQUEST);
        }
    }

    public PostulantExamResponse start(Long eventExamId, Postulant postulant) {
        var examEvent = examEventRepository.findById(eventExamId)
            .orElseThrow(() -> new NotFoundException(ExamEvent.class, eventExamId));
        
        var examAlreadyStarted = postulantExamRepository.existsByPostulantAndEvent(postulant, examEvent);

        if(examAlreadyStarted)
            throw new ExamAlreadyStartedException();

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
            examPartRepository.findByTitleIn(List.of("Examen 1", "Examen 2")), getUnmsmSpec()
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

    public boolean isTaker(Authentication authentication, Long postulantExamId) {
        var postulant = (Postulant) authentication.getPrincipal();

        return this.postulantExamRepository.findById(postulantExamId).map(exam -> {
            // TODO: implement equals()
            return exam.postulant.getId().equals(postulant.getId());
        }).orElse(false);
    }
}
