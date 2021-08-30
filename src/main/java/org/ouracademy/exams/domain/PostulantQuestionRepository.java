package org.ouracademy.exams.domain;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostulantQuestionRepository extends PagingAndSortingRepository<PostulantQuestion, Long> {
	
	@Cacheable("questions")
	// TODO: evaluate @Query("select pq from PostulantQuestion pq join fetch pq.alternativeReferences where pq.number = :questionNumber and pq.postulantExam.id = :examId")
    public Optional<PostulantQuestion> findByNumberAndPostulantExam_Id(Integer questionNumber, Long examId);    
 
}
