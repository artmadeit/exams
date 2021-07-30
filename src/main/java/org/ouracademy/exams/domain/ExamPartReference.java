package org.ouracademy.exams.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.ouracademy.exams.domain.structure.ExamPart;

import lombok.Getter;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ExamPartReference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    ExamPart examPart;
    Integer number;

    /**
     * @apiNote jpa only
     */
    ExamPartReference() {}

    ExamPartReference(ExamPart examPart, Integer number) {
        this.examPart = examPart;
        this.number = number;
    }

    public static List<ExamPartReference> toReferences(List<ExamPart> alternatives) {
        List<ExamPartReference> references = new ArrayList<>();
        for (int i = 0; i < alternatives.size(); i++) {
            ExamPart examPart = alternatives.get(i);
            references.add(new ExamPartReference(examPart, i + 1));
        }
        return references;
    }
}
