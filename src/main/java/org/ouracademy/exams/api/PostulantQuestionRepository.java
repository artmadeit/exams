package org.ouracademy.exams.api;

import org.ouracademy.exams.domain.PostulantQuestion;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostulantQuestionRepository extends PagingAndSortingRepository<PostulantQuestion, Long> {

}
