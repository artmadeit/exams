package org.ouracademy.exams.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.ouracademy.exams.domain.ExamPart;
import org.ouracademy.exams.domain.ExamPartContainer;
import org.ouracademy.exams.domain.BuildExamPartSpecification;
import org.ouracademy.exams.domain.ExamRandomBuilder;
import org.ouracademy.exams.domain.ExamSpecification;
import org.ouracademy.exams.domain.PostulantExam;
import org.ouracademy.exams.domain.Question;

import ch.ifocusit.plantuml.classdiagram.ClassDiagramBuilder;

public class UMLGenerator {
    public static void main(String[] args) throws FileNotFoundException {
        String diagram = new ClassDiagramBuilder()
        .addClasse(
            ExamPart.class, ExamPartContainer.class, Question.class,
            ExamRandomBuilder.class, 
            BuildExamPartSpecification.class,
            ExamSpecification.class,
            PostulantExam.class, PostulantExam.PostulantQuestion.class)
        .build();

        try (PrintWriter out = new PrintWriter("diagram.txt")) {
            out.println(diagram);
        }
    }
}
