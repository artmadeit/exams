package org.ouracademy.exams.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ManyToOne;

import org.ouracademy.exams.domain.structure.ExamPart;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExamPartReference {

    @ManyToOne
    ExamPart examPart;
    Integer number;

    /**
     * @apiNote jpa only
     */
    ExamPartReference() {}

    public static List<ExamPartReference> toReferences(List<ExamPart> alternatives) {
        List<ExamPartReference> references = new ArrayList<>();
        for (int i = 0; i < alternatives.size(); i++) {
            ExamPart examPart = alternatives.get(i);
            references.add(new ExamPartReference(examPart, i + 1));
        }
        return references;
    }
}
