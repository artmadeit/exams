package org.ouracademy.exams.domain;

import static org.ouracademy.exams.domain.build.BuildExamPartSpecification.createExamSpecification;
import static org.ouracademy.exams.domain.build.BuildExamPartSpecification.with;

import java.util.List;

import org.ouracademy.exams.domain.build.BuildExamPartSpecification;
import org.ouracademy.exams.api.PostulantQuestionController.PostulantQuestionResponse;

import org.ouracademy.exams.domain.build.ExamRandomBuilder;
import org.ouracademy.exams.domain.event.ExamEventRepository;
import org.ouracademy.exams.domain.postulant.Postulant;
import org.ouracademy.exams.domain.structure.ExamPart.Type;
import org.ouracademy.exams.domain.structure.ExamPart;

import org.ouracademy.exams.utils.NotFoundException;
import org.ouracademy.exams.domain.structure.ExamPartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@Transactional
@AllArgsConstructor
public class PostulantExamService {

    ExamEventRepository examEventRepository;
    PostulantExamRepository postulantExamRepository;
    ExamPartRepository examPartRepository;
    InscriptionRepository inscriptionRepository;

    public PostulantExamResponse finish(Long id) {
        var postulantExam = postulantExamRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PostulantExam.class));
        postulantExam.finish();

        return new PostulantExamResponse(postulantExam);
    }

    public PostulantExamResultResponse getById(Long id) {
        var postulantExam = postulantExamRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PostulantExam.class));
        var questions = postulantExam.getQuestions().stream().map(p -> {
            var content = new PostulantQuestionResponse(p);
            return new PostulantQuestionResultResponse(content, p.getAnswer());
        }).collect(Collectors.toList());
        Double score = postulantExam.getScore();
        return new PostulantExamResultResponse(score, questions, postulantExam.getPostulant());
    }

    @Getter
    public static class PostulantQuestionResultResponse {
        Long answerId;
        PostulantQuestionResponse content;

        public PostulantQuestionResultResponse(PostulantQuestionResponse content, ExamPart answer

        ) {
            this.content = content;
            this.answerId = answer.getId();
        }

    }

    @Getter
    public static class PostulantExamResultResponse {
        Double score;
        List questions;
        Postulant postulant;


        public PostulantExamResultResponse(Double score, List questions, Postulant postulant) {
        this.score = score;
        this.questions = questions;
        this.postulant = postulant;
        }
    }

    @Getter
    public static class PostulantExamResponse {
        Long id;
        Integer numberOfQuestions;
        DateTimeRange actualRange;

        public PostulantExamResponse(PostulantExam postulantExam) {
            this.id = postulantExam.getId();
            this.numberOfQuestions = postulantExam.getNumberOfQuestions();
            this.actualRange = postulantExam.getActualRange();
        }
    }

    private List<PostulantQuestion> randomQuestions() {
        return new ExamRandomBuilder().from(
                // TODO: get unmsm exam templates
                examPartRepository.findByTitleIn(List.of("Examen 1", "Examen 2")), getUnmsmSpec());
    }

    private BuildExamPartSpecification getUnmsmSpec() {
        return createExamSpecification(List.of(
                with(1, Type.SECTION).title("CAPACIDADES COMUNICATIVAS")
                        .addChild(with(2, Type.TEXT).addChild(with(5, Type.QUESTION))),
                with(1, Type.SECTION).title("CAPACIDADES LOGICO MATEMATICAS").addChild(with(5, Type.QUESTION)),
                with(1, Type.SECTION).title("CAPACIDADES INVESTIGATIVAS").addChild(with(5, Type.QUESTION)),
                with(1, Type.SECTION).title("PENSAMIENTO CRITICO").addChild(with(5, Type.QUESTION))));
    }

    public boolean isTaker(Postulant postulant, Long postulantExamId) {
        var postulantExam = this.postulantExamRepository.findById(postulantExamId);
        return postulant.isTaker(postulantExam);
    }

    @Transactional
    public PostulantExam getOrStart(Long examEventId, Postulant postulant) {
        // TODO: put inscription id
        var examEvent = examEventRepository.getById(examEventId);
        var inscription = inscriptionRepository.findByPostulantAndEvent(postulant, examEvent)
                .orElseThrow(() -> new NotFoundException(Inscription.class));

        if (inscription.getPostulantExam() == null) {
            inscription.setPostulantExam(inscription.startExam(this::randomQuestions));
        }

        return inscription.getPostulantExam();
    }
}
