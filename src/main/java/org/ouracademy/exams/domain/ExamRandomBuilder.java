package org.ouracademy.exams.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.ouracademy.exams.domain.ExamPart.Type;
import org.ouracademy.exams.utils.RandomSampling;

public class ExamRandomBuilder {


    List<PostulantExam.PostulantQuestion> from(List<ExamPartContainer> exams, BuildExamPartSpecification specification) {
        assert exams.stream().allMatch(specification::fulfill);
        
        assert specification.examPartType == Type.EXAM;

        List<PostulantExam.PostulantQuestion> result = new ArrayList<>();

        for (var child: specification.childs) {

            /// LOGICO E1, LOGICO E2
            // var examParts = child.findExamParts(exams);
            // var randomExamPart = RandomSampling.getElement(examParts);
            
            // hello(result, child, randomExamPart);



        }
        
        return result;
    }

    // private void hello(List<PostulantExam.PostulantQuestion> result, ExamPartSpecification specification,
    //         ExamPart randomExamPart) {
    //     for (var child : specification.childs) {
    //         var questions = RandomSampling.getNElements(child.number, randomExamPart.getChilds());
            
    //         var postulantQuestions = questions.stream().map(question -> 
    //             new PostulantExam.PostulantQuestion((Pregunta) question, RandomSampling.ofAll(question.getChilds()))
    //         ).collect(Collectors.toList());

    //         result.addAll(postulantQuestions);
    //     }
    // }
    
}
