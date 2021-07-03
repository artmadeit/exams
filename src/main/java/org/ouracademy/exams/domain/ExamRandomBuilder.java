package org.ouracademy.exams.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import org.ouracademy.exams.domain.ExamPart.Type;

public class ExamRandomBuilder {


    ExamPart from(List<ExamPart> exams, Specification specification) {
        return null;
    }

    public static class SpecificationComposite {
        protected List<SpecificationComposite> childs = new ArrayList<>();
    }
    
    public static class Specification extends SpecificationComposite {

        public LeafSpecification of(Type section, String sectionName) {
            var sectionSpec = new LeafSpecification(section);
            sectionSpec.sectionName = Optional.of(sectionName);

            childs.add(sectionSpec);
            return sectionSpec;
        }

        public LeafSpecification of(Type text) {
            return new LeafSpecification(text);
        }

        public boolean meet(ExamPart randomExam) {
            return true;
        }

    }

    public static class LeafSpecification extends SpecificationComposite {

        private Type section;
        private Optional<String> sectionName = Optional.empty();
        private int numberOfQuestions;

        public LeafSpecification(Type section) {
            this.section = section;
        }

        public void has(int numberOfQuestions, Class<Pregunta> ignoreThis) {
            this.numberOfQuestions = numberOfQuestions;
        }

        public void has(Supplier<Specification> getSpecification) {
            this.childs.add(getSpecification.get());
        }
    }
}
