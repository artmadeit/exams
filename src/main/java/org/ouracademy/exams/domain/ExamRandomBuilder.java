package org.ouracademy.exams.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.ouracademy.exams.domain.ExamPart.Type;
import org.ouracademy.exams.utils.Sampling;

public class ExamRandomBuilder {


    ExamPartContainer from(List<ExamPartContainer> exams, Specification specification) {
        assert exams.stream().allMatch(specification::fulfill);

        var randomExam = new ExamPartContainer();
        randomExam.type = Type.EXAM;
        randomExam.titulo = "Examen";
        
        // for (var child: specification.childs) {
            
        //     var leafSpec = (LeafSpecification) child;
            
        //     var allQuestions = allQuestions(exams, leafSpec.examPartType, leafSpec.title);
        //     var questions = Sampling.getNRandomElements(leafSpec.numberOfQuestions, allQuestions);
            
        //     System.out.println(questions);


        // }
        
        return randomExam;
    }



    private List<ExamPart> allQuestions(List<ExamPartContainer> exams, Type examPartType, String title) {
        return exams.stream()
            .map(exam -> exam.getQuestions(examPartType, title))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    public static class SpecificationComposite {
        protected List<SpecificationComposite> childs = new ArrayList<>();
    }
    
    public static class Specification extends SpecificationComposite {

        public LeafSpecification of(Type examPartType, String title) {
            var sectionSpec = new LeafSpecification(examPartType, title);
            childs.add(sectionSpec);
            return sectionSpec;
        }

        public boolean fulfill(ExamPartContainer exam) {
            return true;
        }

    }

    public static class LeafSpecification extends SpecificationComposite {

        private Type examPartType;
        private String title;
        private int numberOfQuestions;

        public LeafSpecification(Type examPartType, String title) {
            this.examPartType = examPartType;
            this.title = title;
        }

        public void hasQuestions(int numberOfQuestions) {
            this.numberOfQuestions = numberOfQuestions;
        }

        public void has(Supplier<Specification> getSpecification) {
            this.childs.add(getSpecification.get());
        }
    }
}
