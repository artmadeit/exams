package org.ouracademy.exams.domain.structure;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExamRepository extends PagingAndSortingRepository<Exam, Long> {
}
