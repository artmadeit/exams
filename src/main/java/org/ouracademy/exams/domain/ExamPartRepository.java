package org.ouracademy.exams.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExamPartRepository extends PagingAndSortingRepository<ExamPart, Long> {

    List<ExamPart> findByTitleIn(Collection<String> titles);
    
}
