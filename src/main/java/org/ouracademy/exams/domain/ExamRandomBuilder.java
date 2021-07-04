package org.ouracademy.exams.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.ouracademy.exams.domain.ExamPart.Type;
import org.ouracademy.exams.utils.RandomSampling;

public class ExamRandomBuilder {


    List<PostulantExam.PostulantQuestion> from(List<ExamPartContainer> exams, ExamPartSpecification specification) {
        assert exams.stream().allMatch(specification::fulfill);
        
        assert specification.examPartType == Type.EXAM;

        List<PostulantExam.PostulantQuestion> result = new ArrayList<>();

        for (var child: specification.childs) {

            /// LOGICO E1, LOGICO E2
            var examParts = child.findExamParts(exams);
            var randomExamPart = RandomSampling.getElement(examParts);
            
            for (var childchild : child.childs) {
                var questions = RandomSampling.getNElements(childchild.number, randomExamPart.getChilds());
                
                var postulantQuestions = questions.stream().map(question -> 
                    new PostulantExam.PostulantQuestion((Pregunta) question, question.getChilds())
                ).collect(Collectors.toList());

                result.addAll(postulantQuestions);
            }
        }
        
        return result;
    }
    
}
