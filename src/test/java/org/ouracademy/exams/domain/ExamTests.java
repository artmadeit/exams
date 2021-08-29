package org.ouracademy.exams.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.ouracademy.exams.domain.build.BuildExamPartSpecification.createExamSpecification;
import static org.ouracademy.exams.domain.build.BuildExamPartSpecification.with;
import static org.ouracademy.exams.domain.structure.ExamTestData.examen;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.ouracademy.exams.domain.build.BuildExamPartSpecification;
import org.ouracademy.exams.domain.build.ExamRandomBuilder;
import org.ouracademy.exams.domain.event.ExamEvent;
import org.ouracademy.exams.domain.postulant.Postulant;
import org.ouracademy.exams.domain.structure.ExamPart.Type;


public class ExamTests {
    
    @Test
    void test_postulante_inicia_un_examen() {  
        var postulantExam = aPostulantExam();
        assertNotNull(postulantExam);
        assertNotNull(postulantExam.getActualRange().start);
    }

    private PostulantExam aPostulantExam() {
        var postulant = arthur();
        var examEvent = anExamEvent();
        var inscription = new Inscription(postulant, examEvent); 
        var postulantExam = inscription.startExam(() -> List.of());
        return postulantExam;
    }

    @Test
    void test_try_to_start_when_exam_no_started() {  
        var postulant = arthur();
        var examEvent = anExamEvent();
        examEvent.range.start = LocalDateTime.now().plusDays(1);

        var inscription = new Inscription(postulant, examEvent); 
        assertThrows(ExamEvent.NotStartedException.class, () -> inscription.startExam(() -> List.of()));
    }

    @Test
    void test_try_to_start_when_exam_ended() {  
        var postulant = arthur();
        var examEvent = anExamEvent();
        examEvent.range.end = LocalDateTime.now().minusHours(1);

        var inscription = new Inscription(postulant, examEvent);
        assertThrows(ExamEvent.EndedException.class, () -> inscription.startExam(() -> List.of()));
    }

    @Test
    void test_finish() {
        var postulantExam = aPostulantExam();
        postulantExam.finish();
        assertNotNull(postulantExam.getActualRange().getEnd());
    }

    public static ExamEvent anExamEvent() {
        var examEvent = ExamEvent.builder()
            .description("Examen de postgrado 2021 - II")
            .range(new DateTimeRange(
                LocalDateTime.of(2021, 03, 07, 15, 00), LocalDateTime.of(2022, 03, 07, 17, 00) 
            )).build();
        return examEvent;
    }

    private Postulant arthur() {
        return Postulant.builder()
				.code("12123123")
				.dni("73646447")
				.lastName("mauricio")
				.motherLastName("delgadillo")
				.firstName("arthur")
				.programCode("1")
				.upgCode("12")
				.build();
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
            var text1 =  randomQuestions.get(0).getQuestion().getParent();
            assertThat(randomQuestions.subList(0, 5)).allMatch((q) -> q.getQuestion().getParent().getType().equals(Type.TEXT));
            assertThat(randomQuestions.subList(0, 5)).allMatch((q) -> 
                q.getQuestion().getParent().getTitle().equals(text1.getTitle())
            );
            assertThat(randomQuestions.subList(0, 5)).doesNotHaveDuplicates();
            
            var text2 =  randomQuestions.get(5).getQuestion().getParent();
            assertThat(randomQuestions.subList(5, 10)).allMatch((q) -> q.getQuestion().getParent().getType().equals(Type.TEXT));
            assertThat(randomQuestions.subList(5, 10)).allMatch((q) -> 
                q.getQuestion().getParent().getTitle().equals(text2.getTitle())
            );
            assertThat(randomQuestions.subList(5, 10)).doesNotHaveDuplicates();

            assertNotEquals(text1.getTitle(), text2.getTitle());

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
                Collectors.groupingBy(w -> w.getQuestion().getContent(), Collectors.counting())
            );
        grouped.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
        assertEquals(50, grouped.size());
    }

    private void assertSectionOk(List<PostulantQuestion> randomQuestions, String title) {
        assertThat(randomQuestions).allMatch((q) -> q.getQuestion().getParent().getType().equals(Type.SECTION));
        assertThat(randomQuestions).allMatch((q) -> {
            return q.getQuestion().getParent().getTitle().equals(title);
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

    // TODO: uncomment this
    // @Test
    // void test_get_childs_inmutable() throws FileNotFoundException {
    //     var examen1 = examen(1);
    //     var subsectionsExam = examen1.getChilds();
    //     assertEquals(4, subsectionsExam.size());
    //     subsectionsExam.add(ExamPart.section("New section", "desc", exam));

    //     assertEquals(5, subsectionsExam.size());
    //     assertEquals(4, examen1.getChilds().size());
    // }
}
