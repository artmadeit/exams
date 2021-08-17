package org.ouracademy.exams.domain.build;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.jupiter.api.Test;
import org.ouracademy.exams.domain.structure.ExamPart;
import org.ouracademy.exams.domain.structure.ExamTestData;
import org.ouracademy.exams.domain.structure.Question;
import org.ouracademy.exams.utils.BadArgumentsException;

public class ExamStructureTests {
    @Test
    void test_crea_un_examen_word() {
        var examenTestDataGenerator = new ExamTestData();
        var examen2 = examenTestDataGenerator.build(2);
        assertNotNull(examen2);
        assertEquals("Examen 2", examen2.getTitle());
        assertEquals(25, examenTestDataGenerator.getNumeroPreguntas());
    }


    @Test
    void test_validar_child() {
        var question = new Question("una pregunta");
        var section = new ExamPart(ExamPart.Type.SECTION, "seccion 1", null);

        assertThrows(BadArgumentsException.class, () -> question.addChild(section));

        section.addChild(question);
        assertEquals(1, section.getChilds().size());
        assertEquals(section, question.getParent());
    }

    public static void main(String[] args) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter("filename.txt")) {
            out.println(ExamTestData.prettyString(ExamTestData.examen(1), "", ""));
        }
    }
}
