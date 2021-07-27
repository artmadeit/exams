package org.ouracademy.exams;

import java.util.Locale;

import org.ouracademy.exams.utils.BadArgumentsException;
import org.ouracademy.exams.utils.OuracademyException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.zalando.problem.Problem;

import lombok.AllArgsConstructor;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    final MessageSource messageSource;

    @ExceptionHandler(OuracademyException.class)
    public ResponseEntity<Problem> handleIllegalArgument(OuracademyException ex, Locale locale) {
        String errorMessage = messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale);
        var problem = Problem.builder()
            .withDetail(errorMessage)
            .withTitle(ex.getTitle())
            .withType(ex.getType())
            .withStatus(ex.getStatus())
            .build();

        return new ResponseEntity<>(problem, HttpStatus.valueOf(ex.getStatus().getStatusCode()));
    }
}
