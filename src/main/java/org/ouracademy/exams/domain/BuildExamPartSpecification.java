package org.ouracademy.exams.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.ouracademy.exams.domain.ExamPart.Type;

public class BuildExamPartSpecification {
    
    String title; // nullable
    Type examPartType;
    int number;
    List<BuildExamPartSpecification> childs = new ArrayList<>();

    public static BuildExamPartSpecification createExamSpecification() {
        return new BuildExamPartSpecification(1, Type.EXAM);
    }

    public static BuildExamPartSpecification with(int number, Type examPartType) {
        return new BuildExamPartSpecification(number, examPartType);
    }
    
    private BuildExamPartSpecification(int number, Type examPartType) {
        this.number = number;
        this.examPartType = examPartType;
    }

    public BuildExamPartSpecification title(String title) {
        this.title = title;
        return this;
    }

    public BuildExamPartSpecification addChild(BuildExamPartSpecification child) {
        this.childs.add(child);
        return this;
    }

    public boolean fulfill(ExamPartContainer exam) {
        return true;
    }

    public List<ExamPart> findExamParts(List<ExamPartContainer> exams) {
        return exams.stream()
            .map(exam -> exam.findChild(this.examPartType, this.title))
            .collect(Collectors.toList());
    }
}