package org.ouracademy.exams;

import java.util.Locale;

import org.ouracademy.exams.utils.BadArgumentsException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import lombok.AllArgsConstructor;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    final MessageSource messageSource;

    @ExceptionHandler(BadArgumentsException.class)
    public ResponseEntity<Problem> handleIllegalArgument(BadArgumentsException ex, Locale locale) {
        String errorMessage = messageSource.getMessage(ex.getMessage(), ex.getViolations(), locale);
        var problem = Problem.builder()
            .withDetail(errorMessage)
            .withTitle("Bad request")
            .withStatus(Status.BAD_REQUEST)
            .withType(BadArgumentsException.TYPE)
            .with("violations", ex.getViolations())
            .build();

        return new ResponseEntity<>(problem, HttpStatus.BAD_REQUEST);
    }
}
