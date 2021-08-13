package org.ouracademy.exams.domain;

import java.util.Optional;

import org.ouracademy.exams.domain.event.ExamEvent;
import org.ouracademy.exams.domain.postulant.Postulant;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InscriptionRepository extends PagingAndSortingRepository<Inscription, Long> {
    Optional<Inscription> findByPostulantAndEvent(Postulant postulant, ExamEvent examEvent);
}
