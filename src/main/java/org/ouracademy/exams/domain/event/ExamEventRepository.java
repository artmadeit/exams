package org.ouracademy.exams.domain.event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamEventRepository extends JpaRepository<ExamEvent, Long> {

}
