package org.ouracademy.exams.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.ouracademy.exams.domain.PostulantQuestion;

public class NotFoundExceptionTests {
    

    @Test
    void create_exception() {
        var exception = new NotFoundException(PostulantQuestion.class, 1500L);
        assertEquals("not_found.entity_by_id", exception.code);
        assertEquals("postulant_question", exception.args.get("entity"));
        assertEquals(1500L, exception.args.get("id"));
    }

    @Test
    void humanize_class_name() {
        assertEquals("person", NotFoundException.toUnderscore("Person"));
        assertEquals("postulant_question", NotFoundException.toUnderscore("PostulantQuestion"));
    }
}
