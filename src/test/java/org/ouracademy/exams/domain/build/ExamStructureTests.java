package org.ouracademy.exams.domain.build;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.jupiter.api.Test;
import org.ouracademy.exams.domain.structure.ExamTestData;

public class ExamStructureTests {
    @Test
    void test_crea_un_examen_word() {
        var examenTestDataGenerator = new ExamTestData();
        var examen2 = examenTestDataGenerator.build(2);
        assertNotNull(examen2);
        assertEquals("Examen 2", examen2.getTitle());
        assertEquals(25, examenTestDataGenerator.getNumeroPreguntas());
    }

    public static void main(String[] args) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter("filename.txt")) {
            out.println(ExamTestData.prettyString(ExamTestData.examen(1), "", ""));
        }
    }
}
