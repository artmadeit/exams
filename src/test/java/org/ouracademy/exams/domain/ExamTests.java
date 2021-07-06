package org.ouracademy.exams.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.ouracademy.exams.domain.BuildExamPartSpecification.createExamSpecification;
import static org.ouracademy.exams.domain.BuildExamPartSpecification.with;
import static org.ouracademy.exams.domain.ExamTestData.examen;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.ouracademy.exams.domain.ExamPart.Type;


public class ExamTests {
    @Test
    void test_crea_un_examen_word() {
        var examenTestDataGenerator = new ExamTestData();
        var examen2 = examenTestDataGenerator.build(2);
        assertNotNull(examen2);
        assertEquals("Examen 2", examen2.title);
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
        var specification = ExamEvent.builder()
            .description("Examen de postgrado 2021 - II")
            .range(new DateTimeRange(
                LocalDateTime.of(2021, 03, 07, 15, 00), LocalDateTime.of(2021, 03, 07, 17, 00) 
            )).build();
        

        List<PostulantQuestion> randomQuestions = List.of();
        var postulantExam = postulante.start(specification, randomQuestions);
        assertNotNull(postulantExam);
    }

    @Test
    void test_generar_examen_aleatorio() {
        List<PostulantQuestion> allQuestions = new ArrayList<>();

        IntStream.rangeClosed(1, 5000).forEach(i -> {
            System.out.println("i:" + i);
            List<PostulantQuestion> randomQuestions = new ExamRandomBuilder().from(
                List.of(examen(1), examen(2)), getUnmsmSpec()
            );
            
            assertNotNull(randomQuestions);
            assertEquals(25, randomQuestions.size());

            // section 1
            var text1 =  ((ExamPartContainer) randomQuestions.get(0).question.parent);
            assertThat(randomQuestions.subList(0, 5)).allMatch((q) -> q.question.parent.type.equals(Type.TEXT));
            assertThat(randomQuestions.subList(0, 5)).allMatch((q) -> 
                ((ExamPartContainer)q.question.parent).title.equals(text1.title)
            );
            assertThat(randomQuestions.subList(0, 5)).doesNotHaveDuplicates();
            
            var text2 =  ((ExamPartContainer) randomQuestions.get(5).question.parent);
            assertThat(randomQuestions.subList(5, 10)).allMatch((q) -> q.question.parent.type.equals(Type.TEXT));
            assertThat(randomQuestions.subList(5, 10)).allMatch((q) -> 
                ((ExamPartContainer)q.question.parent).title.equals(text2.title)
            );
            assertThat(randomQuestions.subList(5, 10)).doesNotHaveDuplicates();

            assertNotEquals(text1.title, text2.title);

            // section 2
            assertSectionOk(randomQuestions.subList(10, 15), "CAPACIDADES LOGICO MATEMATICAS");
            
            // section 3
            assertSectionOk(randomQuestions.subList(15, 20), "CAPACIDADES INVESTIGATIVAS");
            
            // section 4
            assertSectionOk(randomQuestions.subList(20, 25), "PENSAMIENTO CRITICO");

            allQuestions.addAll(randomQuestions);
        });

        var grouped =
            allQuestions.stream().collect(
                Collectors.groupingBy(w -> w.question.content, Collectors.counting())
            );
        grouped.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
        assertEquals(50, grouped.size());
    }

    private void assertSectionOk(List<PostulantQuestion> randomQuestions, String title) {
        assertThat(randomQuestions).allMatch((q) -> q.question.parent.type.equals(Type.SECTION));
        assertThat(randomQuestions).allMatch((q) -> {
            return ((ExamPartContainer) q.question.parent).title.equals(title);
        });
        assertThat(randomQuestions).doesNotHaveDuplicates();
    }

    private BuildExamPartSpecification getUnmsmSpec() {
        return createExamSpecification(
            List.of(
                with(1, Type.SECTION).title("CAPACIDADES COMUNICATIVAS")
                    .addChild(with(2, Type.TEXT).addChild(with(5, Type.QUESTION))),
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
}
