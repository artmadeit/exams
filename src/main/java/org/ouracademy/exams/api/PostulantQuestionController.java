package org.ouracademy.exams.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.ouracademy.exams.domain.PostulantQuestion;
import org.ouracademy.exams.domain.PostulantQuestionRepository;
import org.ouracademy.exams.domain.structure.ExamPart;
import org.ouracademy.exams.utils.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        Long id;
        Integer number;
        ExamParthWithParent question;
        Long postulantAnswerId;
        List<ExamPartResponse> alternatives = new ArrayList<>();

        public PostulantQuestionResponse(PostulantQuestion postulantQuestion) {
            this.id = postulantQuestion.getId();
            this.number = postulantQuestion.getNumber();
            this.question = new ExamParthWithParent(postulantQuestion.getQuestion());
            this.postulantAnswerId = postulantQuestion.getPostulantAnswer() != null? 
                postulantQuestion.getPostulantAnswer().getId(): null;
            this.alternatives = postulantQuestion.getAlternatives().stream()
                .map(ExamPartResponse::new)
                .collect(Collectors.toList());
        }
        
    }


    @PreAuthorize("@postulantExamService.isTaker(principal, #examId)")
    @GetMapping("{examId}/{questionNumber}")
    public ResponseEntity<PostulantQuestionResponse> get(@PathVariable Long examId, @PathVariable Integer questionNumber) {
        return ResponseEntity.of(
            postulantQuestionRepository.findByNumberAndPostulantExam_Id(questionNumber, examId)
            .map(PostulantQuestionResponse::new)
        );
    }


    @Data
    public static class AnswerRequest {
        Long alternativeId;
    }

    @PreAuthorize("@postulantExamService.isTaker(principal, #examId)")
    @PutMapping("{examId}/{questionNumber}/answer")
    @Transactional
    public void updateAnswer(
        @PathVariable Long examId, @PathVariable Integer questionNumber, @RequestBody AnswerRequest answer) {
        
        var postulantQuestion = postulantQuestionRepository
            .findByNumberAndPostulantExam_Id(questionNumber, examId)
            .orElseThrow(() -> new NotFoundException(PostulantQuestion.class));
        
        postulantQuestion.updateAnswer(answer.alternativeId);
    }
}
