package org.ouracademy.exams.api;

import java.util.Collection;
import java.util.List;

import org.ouracademy.exams.domain.ExamPart;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExamPartRepository extends PagingAndSortingRepository<ExamPart, Long> {

    List<ExamPart> findByTitleIn(Collection<String> titles);
    
}
