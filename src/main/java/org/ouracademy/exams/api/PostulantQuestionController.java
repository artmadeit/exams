package org.ouracademy.exams.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.ouracademy.exams.domain.ExamPart;
import org.ouracademy.exams.domain.PostulantQuestion;
import org.ouracademy.exams.utils.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Getter;

@RestController
@RequestMapping("/postulant-question")
@AllArgsConstructor
public class PostulantQuestionController {
    PostulantQuestionRepository postulantQuestionRepository;
    ExamPartRepository examPartRepository;


    @Getter
    public static class ExamPartResponse {

        Long id;
        String content;
        ExamPart.Type type;
        String title;

        ExamPartResponse(ExamPart examPart) {
            this.id = examPart.getId();
            this.content = examPart.getContent();
            this.type = examPart.getType();
            this.title = examPart.getTitle();
        }
    }

    @Getter
    public static class ExamParthWithParent extends ExamPartResponse {
        ExamPartResponse parent;

        ExamParthWithParent(ExamPart examPart) {
            super(examPart);
            if(!examPart.getParent().getType().equals(ExamPart.Type.EXAM))
                this.parent = new ExamParthWithParent(examPart.getParent());
        }
    }

    @Getter
    public static class PostulantQuestionResponse{
        
        ExamParthWithParent question;
        ExamPart postulantAnswer;
        List<ExamPartResponse> alternatives = new ArrayList<>();

        public PostulantQuestionResponse(PostulantQuestion postulantQuestion) {
            this.question = new ExamParthWithParent(postulantQuestion.getQuestion());
            this.postulantAnswer = postulantQuestion.getPostulantAnswer();
            this.alternatives = postulantQuestion.getAlternatives().stream()
                .map(ExamPartResponse::new)
                .collect(Collectors.toList());
        }
        
    }

    @GetMapping("/{id}")
    public PostulantQuestionResponse get(@PathVariable Long id) {
        var postulantQuestion = postulantQuestionRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(PostulantQuestion.class, id));

        return new PostulantQuestionResponse(postulantQuestion);
    }

    @PutMapping("/{id}/answer/{answerId}")
    @Transactional
    public PostulantQuestion updateAnswer(@PathVariable Long id, @PathVariable Long answerId) {
        // TODO: que pasa si quita respuesta?
        // otra api?
        // validar con diana

        var postulantQuestion = postulantQuestionRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(PostulantQuestion.class, id));

        var postulantAnswer = examPartRepository.findById(answerId)
            .orElseThrow(() -> new NotFoundException("Not found postulant answer with id:" + id));
        
        postulantQuestion.setPostulantAnswer(postulantAnswer);
        return postulantQuestion;
    }
}
