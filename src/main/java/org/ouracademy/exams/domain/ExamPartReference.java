package org.ouracademy.exams.domain;

import org.ouracademy.exams.domain.structure.ExamPart;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExamPartReference {
    
    ExamPart examPart;
    Integer number;
}
