package org.ouracademy.exams.domain.postulant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.ouracademy.exams.domain.ExamTests;
import org.ouracademy.exams.domain.Inscription;

public class PostulantTests {
    Postulant arthur = Postulant.builder().code("123").dni("73646447").firstName("arthur").lastName("mauricio")
            .motherLastName("delgadillo").programCode("sw").upgCode("fisi").build();

    Postulant diana = Postulant.builder().code("223").dni("48484498").firstName("diana").lastName("quintanilla")
            .motherLastName("perez").programCode("sw").upgCode("fisi").build();

    @Test
    void test_constructor() {
        assertEquals("123", arthur.getCode());
        assertEquals("73646447", arthur.getDni());
        assertEquals("arthur", arthur.getFirstName());
        assertEquals("mauricio", arthur.getLastName());
        assertEquals("delgadillo", arthur.getMotherLastName());
        assertEquals("sw", arthur.getProgramCode());
        assertEquals("fisi", arthur.getUpgCode());
    }

    @Test
    void test_is_taker() {
        var arthurExam = new Inscription(arthur, ExamTests.anExamEvent()).startExam(() -> Collections.emptyList());
        var dianaExam = new Inscription(diana, ExamTests.anExamEvent()).startExam(() -> Collections.emptyList());

        assertFalse(arthur.isTaker(Optional.empty()));
        assertTrue(arthur.isTaker(Optional.of(arthurExam)));
        assertFalse(arthur.isTaker(Optional.of(dianaExam)));
    }

    @Test
    void test_equals() {
        assertEquals(arthur, arthur);
        assertNotEquals(arthur, diana);

        var arthurRetrievedByDB = Postulant.builder().code("123").dni("73646447").firstName("arthur")
                .lastName("mauricio").motherLastName("delgadillo").programCode("sw").upgCode("fisi").build();

        assertEquals(arthur, arthurRetrievedByDB);
    }
}
