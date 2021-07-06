package org.ouracademy.exams.api;

import org.ouracademy.exams.domain.Postulant;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostulantRepository extends PagingAndSortingRepository<Postulant, Long> {
    
}
