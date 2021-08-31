package org.ouracademy.exams.domain;

import java.util.List;

import com.google.common.collect.Lists;

import org.ouracademy.exams.domain.structure.ExamPart;
import org.ouracademy.exams.domain.structure.ExamPartRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostulantQuestionService {
    
    ExamPartRepository examPartRepository;

    public List<ExamPart> getAlternatives(PostulantQuestion postulantQuestion) {
        return Lists.newArrayList(examPartRepository.findAllById(postulantQuestion.getAlternativeIds()));
    }
}
