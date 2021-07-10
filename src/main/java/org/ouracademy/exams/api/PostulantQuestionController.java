package org.ouracademy.exams.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.ouracademy.exams.domain.ExamPart;
import org.ouracademy.exams.domain.ExamPartRepository;
import org.ouracademy.exams.domain.PostulantQuestion;
import org.ouracademy.exams.domain.PostulantQuestionRepository;
import org.ouracademy.exams.utils.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
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

        ExamPartResponse(ExamPart examPart) {
            this.id = examPart.getId();
            this.content = examPart.getContent();
        }
    }

    @Getter
    public static class ExamParthWithParent extends ExamPartResponse {
        
        ExamPart.Type type;
        String title;
        ExamPartResponse parent;

        ExamParthWithParent(ExamPart examPart) {
            super(examPart);
            
            this.type = examPart.getType();
            this.title = examPart.getTitle();

            if(!examPart.getParent().getType().equals(ExamPart.Type.EXAM))
                this.parent = new ExamParthWithParent(examPart.getParent());
        }
    }

    @Getter
    public static class PostulantQuestionResponse{
        
        ExamParthWithParent question;
        Long postulantAnswerId;
        List<ExamPartResponse> alternatives = new ArrayList<>();

        public PostulantQuestionResponse(PostulantQuestion postulantQuestion) {
            this.question = new ExamParthWithParent(postulantQuestion.getQuestion());
            this.postulantAnswerId = postulantQuestion.getPostulantAnswer() != null? 
                postulantQuestion.getPostulantAnswer().getId(): null;
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


    @Data
    public static class Answer {
        Long id;
    }

    @PutMapping("/{id}/answer")
    @Transactional
    public PostulantQuestionResponse updateAnswer(@PathVariable Long id, @RequestBody Answer answer) {
        var postulantQuestion = postulantQuestionRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(PostulantQuestion.class, id));
            
        var postulantAnswer = answer.id == null? 
            null:
            examPartRepository.findById(answer.id).orElseThrow(() -> new NotFoundException("Not found answer with answerId:" + answer.id));
        
        postulantQuestion.setPostulantAnswer(postulantAnswer);
        return new PostulantQuestionResponse(postulantQuestion);
    }
}
