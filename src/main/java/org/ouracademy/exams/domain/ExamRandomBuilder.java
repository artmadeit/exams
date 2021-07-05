package org.ouracademy.exams.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.ouracademy.exams.domain.ExamPart.Type;
import org.ouracademy.exams.domain.PostulantExam.PostulantQuestion;
import org.ouracademy.exams.utils.RandomSampling;

public class ExamRandomBuilder {

    public List<PostulantQuestion> from(List<ExamPart> exams, BuildExamPartSpecification specification) {
        if(specification.examPartType != Type.EXAM)
            throw new IllegalArgumentException("Spec isn't of exam, spec.type" + specification.examPartType);
        
        return getPostulantQuestions(exams, List.of(specification));
    }

    private List<PostulantQuestion> getPostulantQuestions(List<ExamPart> examParts, List<BuildExamPartSpecification> specifications) {
        List<PostulantQuestion> result = new ArrayList<>();

        for (BuildExamPartSpecification spec : specifications) {
            if(spec.examPartType.equals(Type.EXAM)) {
                result.addAll(getPostulantQuestions(childs(examParts), spec.childs));
            }
            if(spec.examPartType.equals(Type.QUESTION)) {
                var randomExamParts = RandomSampling.getNUniqueElements(spec.number, examParts);
                result.addAll(question((List<Question>) (List<?>) randomExamParts));
            }
            if(spec.examPartType.equals(Type.SECTION)) {
                var examPartsMeetingSpec = spec.findExamParts(examParts);
                result.addAll(getPostulantQuestions(childs(examPartsMeetingSpec), spec.childs));
            }
            if(spec.examPartType.equals(Type.TEXT)) {
                var examPartsMeetingSpec = spec.findExamParts(examParts);
                var randomExamPartsMeetingSpec = RandomSampling.getNUniqueElements(spec.number, examPartsMeetingSpec);
                result.addAll(getPostulantQuestions(childs(randomExamPartsMeetingSpec), spec.childs));    
            }
        }

        return result;
    }

    private List<ExamPart> childs(List<ExamPart> examParts) {
        return examParts.stream()
            .map(ExamPart::getChilds)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    private List<PostulantQuestion> question(List<Question> randomQuestions) {
        return randomQuestions.stream().map(question -> 
            new PostulantQuestion(question, RandomSampling.ofAll(question.alternatives()))
        ).collect(Collectors.toList());
    }

}
