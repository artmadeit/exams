package org.ouracademy.exams.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.ouracademy.exams.domain.PostulantExam;
import org.ouracademy.exams.domain.PostulantQuestion;
import org.ouracademy.exams.domain.build.BuildExamPartSpecification;
import org.ouracademy.exams.domain.build.ExamRandomBuilder;
import org.ouracademy.exams.domain.postulant.Postulant;
import org.ouracademy.exams.domain.structure.ExamPart;
import org.ouracademy.exams.domain.structure.Question;
import org.ouracademy.exams.event.ExamEvent;

import ch.ifocusit.plantuml.classdiagram.ClassDiagramBuilder;

public class UMLGenerator {
    public static void main(String[] args) throws FileNotFoundException {
        String diagram = new ClassDiagramBuilder()
        .addClasse(
            ExamPart.class, Question.class,
            ExamRandomBuilder.class, 
            BuildExamPartSpecification.class,
            ExamEvent.class,
            PostulantExam.class, PostulantQuestion.class,
            Postulant.class)
        .build();

        try (PrintWriter out = new PrintWriter("diagram.txt")) {
            out.println(diagram);
        }
    }
}
