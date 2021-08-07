package org.ouracademy.exams.domain.structure;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExamPartRepository extends PagingAndSortingRepository<ExamPart, Long> {

    @Transactional(readOnly = true)
    List<ExamPart> findByTitleIn(Collection<String> titles);

    Page<ExamPart> findByType(ExamPart.Type type, Pageable pageable);

    
}
