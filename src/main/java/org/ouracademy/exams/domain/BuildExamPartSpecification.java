package org.ouracademy.exams.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.ouracademy.exams.domain.ExamPart.Type;

public class BuildExamPartSpecification {
    
    String title; // nullable
    Type examPartType;
    int number;
    List<BuildExamPartSpecification> childs = new ArrayList<>();

    public static BuildExamPartSpecification createExamSpecification(List<BuildExamPartSpecification> childs) {
        var spec = new BuildExamPartSpecification(1, Type.EXAM);
        spec.childs = childs;
        return spec;
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

    public boolean fulfill(ExamPart exam) {
        return true;
    }

    public List<ExamPart> findExamParts(List<ExamPart> exams) {
        return exams.stream()
            .filter(x -> {
                if (x instanceof ExamPartContainer examPartContainer) {
                    return title != null? 
                        examPartContainer.type.equals(examPartType) && examPartContainer.titulo.equals(title):
                        examPartContainer.type.equals(examPartType);
                }
    
                return false;
            })
            .collect(Collectors.toList());
    }
}