package org.ouracademy.exams.domain;

import java.util.Optional;

import org.ouracademy.exams.domain.event.ExamEvent;
import org.ouracademy.exams.domain.postulant.Postulant;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostulantExamRepository extends PagingAndSortingRepository<PostulantExam, Long>, JpaSpecificationExecutor<PostulantExam> {

    Optional<PostulantExam> findByPostulantAndEvent(Postulant postulant, ExamEvent examEvent);

}
