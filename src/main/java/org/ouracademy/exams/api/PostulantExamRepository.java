package org.ouracademy.exams.api;

import org.ouracademy.exams.domain.PostulantExam;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostulantExamRepository extends PagingAndSortingRepository<PostulantExam, Long> {

}
