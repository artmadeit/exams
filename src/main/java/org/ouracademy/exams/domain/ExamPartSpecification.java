package org.ouracademy.exams.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.ouracademy.exams.domain.ExamPart.Type;

public class ExamPartSpecification {
    protected List<ExamPartSpecification> childs = new ArrayList<>();

    public static class ExamPartContainerSpecification extends ExamPartSpecification {

        public QuestionSpecification of(Type examPartType, String title) {
            var sectionSpec = new QuestionSpecification(examPartType, title);
            childs.add(sectionSpec);
            return sectionSpec;
        }

        public boolean fulfill(ExamPartContainer exam) {
            return true;
        }

    }

    public static class QuestionSpecification extends ExamPartSpecification {

        private Type examPartType;
        private String title;
        private int numberOfQuestions;

        public QuestionSpecification(Type examPartType, String title) {
            this.examPartType = examPartType;
            this.title = title;
        }

        public void hasQuestions(int numberOfQuestions) {
            this.numberOfQuestions = numberOfQuestions;
        }

        public void has(Supplier<ExamPartContainerSpecification> getSpecification) {
            this.childs.add(getSpecification.get());
        }
    }
}