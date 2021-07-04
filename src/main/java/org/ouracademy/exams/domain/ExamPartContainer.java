package org.ouracademy.exams.domain;

import java.util.List;

// un examen => secciones
// una seccion => textos | preguntas
// una texto => preguntas
public class ExamPartContainer extends ExamPart {

    String titulo;
    
    public List<ExamPart> getQuestions(Type section, String sectionName) {
        return null;
    }

    public ExamPart findChild(Type examPartType, String title) {
        return this.getChilds().stream().filter(x -> {
            if (x instanceof ExamPartContainer examPartContainer) {
                return examPartContainer.type.equals(examPartType) && examPartContainer.titulo.equals(title);
            }

            return false;
        }).findFirst().orElseThrow();
    }
}
