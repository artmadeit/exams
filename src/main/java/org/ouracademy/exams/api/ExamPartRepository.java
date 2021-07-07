package org.ouracademy.exams.api;

import org.ouracademy.exams.domain.ExamPart;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExamPartRepository extends PagingAndSortingRepository<ExamPart, Long> {
    
}
