package org.ouracademy.exams.event;

import org.ouracademy.exams.domain.ExamEvent;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExamEventRepository extends PagingAndSortingRepository<ExamEvent, Long> {

}
