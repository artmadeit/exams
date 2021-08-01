package org.ouracademy.exams.domain;

import static org.ouracademy.exams.domain.build.BuildExamPartSpecification.createExamSpecification;
import static org.ouracademy.exams.domain.build.BuildExamPartSpecification.with;

import java.net.URI;
import java.util.List;

import org.ouracademy.exams.domain.build.BuildExamPartSpecification;
import org.ouracademy.exams.domain.build.ExamRandomBuilder;
import org.ouracademy.exams.domain.event.ExamEvent;
import org.ouracademy.exams.domain.event.ExamEventRepository;
import org.ouracademy.exams.domain.postulant.Postulant;
import org.ouracademy.exams.domain.structure.ExamPart.Type;
import org.ouracademy.exams.domain.structure.ExamPartRepository;
import org.ouracademy.exams.utils.NotFoundException;
import org.ouracademy.exams.utils.OuracademyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

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

    public static class ExamAlreadyStartedException extends OuracademyException {
        private static final URI ERROR_TYPE = URI.create("https://our-academy.org/start-exam-already-started");

        public ExamAlreadyStartedException() {
            super("exam.already_started", "Exam already started", ERROR_TYPE);
        }

        @Override
        public StatusType getStatus() {
            return Status.BAD_REQUEST;
        }
    }


    private PostulantExamResponse start(ExamEvent examEvent, Postulant postulant) {
        var examAlreadyStarted = postulantExamRepository.existsByPostulantAndEvent(postulant, examEvent);

        if(examAlreadyStarted)
            throw new ExamAlreadyStartedException();

        var postulantExam = postulant.start(examEvent, randomQuestions());
        postulantExamRepository.save(postulantExam);
        return new PostulantExamResponse(postulantExam);
    }


    @Getter
    public static class PostulantExamResponse {
        Long id;
        Integer numberOfQuestions;

        PostulantExamResponse(PostulantExam postulantExam) {
            this.id = postulantExam.getId();
            this.numberOfQuestions = postulantExam.getQuestions().size();
                
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

    public boolean isTaker(Postulant postulant, Long postulantExamId) {
        var postulantExam = this.postulantExamRepository.findById(postulantExamId);
        return postulant.isTaker(postulantExam);
    }

    public PostulantExamResponse startOrGet(Long eventExamId, Postulant postulant) {
        var examEvent = examEventRepository.findById(eventExamId)
            .orElseThrow(() -> new NotFoundException(ExamEvent.class, eventExamId));
        
        try {
            return this.start(examEvent, postulant);
        } catch(ExamAlreadyStartedException alreadyStartedException) {
            var postulantExam = postulantExamRepository.findByPostulantAndEvent(postulant, examEvent)
                .orElseThrow(() -> new NotFoundException("exam_by_postulant_id", new Object[] {postulant.getId(), examEvent.getId()}));

            return new PostulantExamResponse(postulantExam);
        }
    }
}
