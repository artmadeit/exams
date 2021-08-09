package org.ouracademy.exams.domain.structure;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ExamPartRepository extends PagingAndSortingRepository<ExamPart, Long> {

    List<ExamPart> findByTitleIn(Collection<String> titles);    
}
