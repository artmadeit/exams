package org.ouracademy.exams.api;

import org.ouracademy.exams.domain.Postulant;
import org.ouracademy.exams.domain.PostulantExam;
import org.ouracademy.exams.event.ExamEvent;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostulantExamRepository extends PagingAndSortingRepository<PostulantExam, Long> {

    boolean existsByPostulantAndEvent(Postulant postulant, ExamEvent examEvent);

}
