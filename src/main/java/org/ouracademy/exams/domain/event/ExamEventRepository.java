package org.ouracademy.exams.domain.event;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExamEventRepository extends PagingAndSortingRepository<ExamEvent, Long> {

}
