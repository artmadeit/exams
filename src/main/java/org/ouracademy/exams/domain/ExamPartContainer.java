package org.ouracademy.exams.domain;

import java.util.List;
import java.util.stream.Collectors;

// un examen => secciones
// una seccion => textos | preguntas
// una texto => preguntas
public class ExamPartContainer extends ExamPart {

    String titulo;
    
    public List<ExamPart> getQuestions(Type section, String sectionName) {
        return null;
    }

    public List<ExamPart> filterChilds(Type examPartType, String title) {
        return this.getChilds().stream().filter(x -> {
            if (x instanceof ExamPartContainer examPartContainer) {
                return title != null? 
                    examPartContainer.type.equals(examPartType) && examPartContainer.titulo.equals(title):
                    examPartContainer.type.equals(examPartType);
            }

            return false;
        }).collect(Collectors.toList());
    }
}
