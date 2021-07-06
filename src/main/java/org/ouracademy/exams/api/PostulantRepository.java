package org.ouracademy.exams.api;

import org.ouracademy.exams.domain.Postulant;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostulantRepository extends PagingAndSortingRepository<Postulant, Long> {
    
}
