package org.ouracademy.exams.domain;

import org.ouracademy.exams.event.ExamEvent;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostulantExamRepository extends PagingAndSortingRepository<PostulantExam, Long> {

    boolean existsByPostulantAndEvent(Postulant postulant, ExamEvent examEvent);

}
