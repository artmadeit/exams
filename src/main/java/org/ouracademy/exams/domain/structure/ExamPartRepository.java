package org.ouracademy.exams.domain.structure;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ExamPartRepository extends JpaRepository<ExamPart, Long> {

    List<ExamPart> findByTitleIn(Collection<String> titles);    
}
