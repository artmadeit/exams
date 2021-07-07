package org.ouracademy.exams.api;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.ouracademy.exams.domain.PostulantQuestion;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/postulant-question")
@AllArgsConstructor
public class PostulantQuestionController {
    PostulantQuestionRepository postulantQuestionRepository;
    ExamPartRepository examPartRepository;

    @PutMapping("/{id}/answer/{answerId}")
    @Transactional
    public PostulantQuestion updateAnswer(@PathVariable Long id, @PathVariable Long answerId) {
        // TODO: que pasa si quita respuesta?
        // otra api?
        // validar con diana

        var postulantQuestion = postulantQuestionRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Not found postulant question with id:" + id));

        var postulantAnswer = examPartRepository.findById(answerId)
            .orElseThrow(() -> new NoSuchElementException("Not found postulant answer with id:" + id));
        
        postulantQuestion.setPostulantAnswer(postulantAnswer);
        return postulantQuestion;
    }
}
