package org.ouracademy.exams.domain;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostulantQuestionRepository extends PagingAndSortingRepository<PostulantQuestion, Long> {
    public Optional<PostulantQuestion> findByNumberAndPostulantExam_Id(Integer questionNumber, Long examId);

    
 
}
