package org.ouracademy.exams.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.ouracademy.exams.domain.ExamTestData.examen;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ouracademy.exams.domain.ExamPart.Type;
import org.ouracademy.exams.domain.PostulantExam.PostulantQuestion;

import static org.ouracademy.exams.domain.BuildExamPartSpecification.*;

public class ExamTests {
    @Test
    void test_crea_un_examen_word() {
        var examenTestDataGenerator = new ExamTestData();
        var examen2 = examenTestDataGenerator.build(2);
        assertNotNull(examen2);
        assertEquals("Examen 2", examen2.titulo);
        assertEquals(25, examenTestDataGenerator.numeroPreguntas);
    }

    public static void main(String[] args) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter("filename.txt")) {
            out.println(ExamTestData.prettyString(examen(1), "", ""));
        }
    }

    @Test
    void test_postulante_inicia_un_examen() {
        var postulante = new Postulante();
        var specification = ExamSpecification.builder()
            .description("Examen de postgrado 2021 - II")
            .range(new DateTimeRange(
                LocalDateTime.of(2021, 03, 07, 15, 00), LocalDateTime.of(2021, 03, 07, 17, 00) 
            )).build();
        

        List<PostulantExam.PostulantQuestion> randomQuestions = List.of();
        var postulantExam = postulante.start(specification, randomQuestions);
        assertNotNull(postulantExam);
    }

    @Test
    void test_generar_examen_aleatorio() throws FileNotFoundException {
        var spec = getUnmsmSpec();
        var randomQuestions = new ExamRandomBuilder().from(
            List.of(examen(1), examen(2)), spec
        );
        
        assertNotNull(randomQuestions);
        assertEquals(25, randomQuestions.size());

        String r = "";
        for (PostulantQuestion postulantQuestion : randomQuestions) {
            r += postulantQuestion.question.content + "\n";

            for (var alternativa: postulantQuestion.alternativas) {
                r += "\t" +alternativa.content + "\n";
            }
        }

        try (PrintWriter out = new PrintWriter("random.txt")) {
            out.println(r);
        }

        // assertEquals(2, randomExam.childs.get(0).childs.size());
        
        // // assertEquals(5, randomExam.childs.get(0).childs.get(0).childs.size());
        // // assertEquals(5, randomExam.childs.get(0).childs.get(1).childs.size());

        // assertEquals(5, randomExam.childs.get(1).childs.size());
        // assertEquals(5, randomExam.childs.get(2).childs.size());
        // assertEquals(5, randomExam.childs.get(3).childs.size()); 
    }

    private BuildExamPartSpecification getUnmsmSpec() {
        return createExamSpecification(
            List.of(
                with(1, Type.SECTION).title("CAPACIDADES COMUNICATIVAS")
                    .addChild(with(1, Type.TEXT).addChild(with(5, Type.QUESTION)))
                    .addChild(with(1, Type.TEXT).addChild(with(5, Type.QUESTION))),
                with(1, Type.SECTION).title("CAPACIDADES LOGICO MATEMATICAS")
                    .addChild(with(5, Type.QUESTION)),
                with(1, Type.SECTION).title("CAPACIDADES INVESTIGATIVAS")
                    .addChild(with(5, Type.QUESTION)),
                with(1, Type.SECTION).title("PENSAMIENTO CRITICO")
                    .addChild(with(5, Type.QUESTION))
            ));
    }

    @Test
    void test_get_childs_inmutable() throws FileNotFoundException {
        var examen1 = examen(1);
        var subsectionsExam = examen1.getChilds();
        assertEquals(4, subsectionsExam.size());
        subsectionsExam.add(new ExamPart());

        assertEquals(5, subsectionsExam.size());
        assertEquals(4, examen1.getChilds().size());
    }
    
    
    @Test
    void test_filter_childs_by_type_and_or_title() throws FileNotFoundException {
        assertEquals(1, examen(1).filterChilds(Type.SECTION, "CAPACIDADES COMUNICATIVAS").size());

        var capacidadesComunicativas = examen(1).filterChilds(Type.SECTION, "CAPACIDADES COMUNICATIVAS").get(0);
        assertNotNull(capacidadesComunicativas);
        
        var lectures = ((ExamPartContainer) capacidadesComunicativas).filterChilds(Type.TEXT, null);
        assertEquals(2, lectures.size());
    }
}
