package org.ouracademy.exams.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.ouracademy.exams.domain.PostulantQuestion;

public class NotFoundExceptionTests {
    

    @Test
    void create_exception() {
        var exception = new NotFoundException(PostulantQuestion.class);
        assertEquals("not_found.postulant_question", exception.code);
    }

    @Test
    void humanize_class_name() {
        assertEquals("person", NotFoundException.toUnderscore("Person"));
        assertEquals("postulant_question", NotFoundException.toUnderscore("PostulantQuestion"));
    }
}
