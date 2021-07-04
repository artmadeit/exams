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
import org.ouracademy.exams.domain.ExamPartSpecification.ExamPartContainerSpecification;

public class ExamTests {
    @Test
    public void test_crea_un_examen_word() {
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
    public void test_postulante_inicia_un_examen() {
        var postulante = new Postulante();
        var specification = ExamSpecification.builder()
            .description("Examen de postgrado 2021 - II")
            .range(new DateTimeRange(
                LocalDateTime.of(2021, 03, 07, 15, 00), LocalDateTime.of(2021, 03, 07, 17, 00) 
            )).build();
        

        var examenAleatorio = new ExamPartContainer();

        var postulantExam = postulante.start(examenAleatorio, specification);
        assertNotNull(postulantExam);
    }

    public void test_generar_examen_aleatorio() {
        var spec = getUnmsmSpec();
        var randomExam = new ExamRandomBuilder().from(
            List.of(examen(1), examen(2)), spec
        );
        
        assertNotNull(randomExam);
        // assertTrue(spec.fulfill(randomExam));

        // assertEquals(2, randomExam.childs.get(0).childs.size());
        
        // // assertEquals(5, randomExam.childs.get(0).childs.get(0).childs.size());
        // // assertEquals(5, randomExam.childs.get(0).childs.get(1).childs.size());

        // assertEquals(5, randomExam.childs.get(1).childs.size());
        // assertEquals(5, randomExam.childs.get(2).childs.size());
        // assertEquals(5, randomExam.childs.get(3).childs.size()); 
    }

    private ExamPartContainerSpecification getUnmsmSpec() {
        var spec = new ExamPartContainerSpecification();
        spec.of(Type.SECTION, "CAPACIDADES COMUNICATIVAS");
            // .has(() -> {
            //     var sectionSpec = new ExamRandomBuilder.Specification();
            //     sectionSpec.of(Type.TEXT, "TEXTO 1").hasQuestions(5);
            //     sectionSpec.of(Type.TEXT, "TEXTO 2").hasQuestions(5);
            //     return sectionSpec;
            // });
        spec.of(Type.SECTION, "CAPACIDADES LOGICO MATEMATICAS").hasQuestions(5);
        spec.of(Type.SECTION, "CAPACIDADES INVESTIGATIVAS").hasQuestions(5);
        spec.of(Type.SECTION, "PENSAMIENTO CRITICO").hasQuestions(5);
        return spec;
    }
    
}
