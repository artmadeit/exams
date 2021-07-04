package org.ouracademy.exams.domain;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.ouracademy.exams.domain.ExamPart.Type;

public class ExamRandomBuilder {


    List<PostulantExam.PostulantQuestion> from(List<ExamPartContainer> exams, ExamPartSpecification specification) {
        assert exams.stream().allMatch(specification::fulfill);

        
        // for (var child: specification.childs) {
            
        //     var leafSpec = (LeafSpecification) child;
            
        //     var allQuestions = allQuestions(exams, leafSpec.examPartType, leafSpec.title);
        //     var questions = Sampling.getNRandomElements(leafSpec.numberOfQuestions, allQuestions);
            
        //     System.out.println(questions);


        // }
        
        return List.of();
    }



    private List<ExamPart> allQuestions(List<ExamPartContainer> exams, Type examPartType, String title) {
        return exams.stream()
            .map(exam -> exam.getQuestions(examPartType, title))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    
}
