package org.ouracademy.exams.api;

import java.util.Collection;
import java.util.List;

import org.ouracademy.exams.domain.ExamPart;
import org.ouracademy.exams.domain.ExamPartContainer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExamPartContainerRepository extends PagingAndSortingRepository<ExamPartContainer, Long> {
    List<ExamPart> findByTitleIn(Collection<String> ages);
}
