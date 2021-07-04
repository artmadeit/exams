package org.ouracademy.exams.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.ouracademy.exams.domain.ExamPart.Type;

public class ExamPartSpecification {
    
    Type examPartType;
    String title; // nullable
    int number = 1;
    List<ExamPartSpecification> childs = new ArrayList<>();

    public ExamPartSpecification of(Type examPartType, String title) {
        var sectionSpec = new ExamPartSpecification(examPartType, title);
        childs.add(sectionSpec);
        return sectionSpec;
    }

    public boolean fulfill(ExamPartContainer exam) {
        return true;
    }

    public ExamPartSpecification() {
        this(Type.EXAM, null);
    }

    public ExamPartSpecification(Type examPartType, String title) {
        this.examPartType = examPartType;
        this.title = title;
    }

    public void hasQuestions(int number) {
        var spec = new ExamPartSpecification(Type.QUESTION, null);
        spec.number = number;
        this.childs.add(spec);
    }

    public void has(Supplier<ExamPartSpecification> getSpecification) {
        this.childs.add(getSpecification.get());
    }
}