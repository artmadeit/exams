package org.ouracademy.exams.domain.build;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.ouracademy.exams.domain.PostulantQuestion;
import org.ouracademy.exams.domain.structure.ExamPart;
import org.ouracademy.exams.domain.structure.Question;
import org.ouracademy.exams.domain.structure.ExamPart.Type;
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
                result.addAll(toPostulantQuestions(randomExamParts));
            }
            if(spec.examPartType.equals(Type.SECTION)) {
                var examPartsMeetingSpec = spec.findExamParts(examParts);
                result.addAll(getPostulantQuestions(childs(examPartsMeetingSpec), spec.childs));
            }
            if(spec.examPartType.equals(Type.TEXT)) {
                var examPartsMeetingSpec = spec.findExamParts(examParts);
                var randomExamPartsMeetingSpec = RandomSampling.getNUniqueElements(spec.number, examPartsMeetingSpec);
                
                for (var text: randomExamPartsMeetingSpec) {
                    result.addAll(getPostulantQuestions(text.getChilds(), spec.childs));    
                }
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

    private List<PostulantQuestion> toPostulantQuestions(List<ExamPart> randomQuestions) {
        assert randomQuestions.stream().allMatch(x -> x.getType().equals(Type.QUESTION));

        return randomQuestions.stream().map(examPart -> {
            var question = (Question) examPart;
            return new PostulantQuestion(question, RandomSampling.ofAll(question.alternatives()));
        }).collect(Collectors.toList());
    }

}
