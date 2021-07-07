package org.ouracademy.exams.api;

import org.ouracademy.exams.domain.PostulantExam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostulantExamService {
    PostulantExamRepository postulantExamRepository;

    @Transactional
    public PostulantExam finish(Long id) {
        var postulantExam = postulantExamRepository.findById(id).orElseThrow();
        postulantExam.finish();
        
        return postulantExam;
    }
}
