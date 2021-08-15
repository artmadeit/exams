package org.ouracademy.exams;

import java.util.Locale;

import org.ouracademy.exams.utils.OuracademyException;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.security.AuthenticationAdviceTrait;

import lombok.AllArgsConstructor;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler implements AuthenticationAdviceTrait {
    final MessageSource messageSource;

    @ExceptionHandler(OuracademyException.class)
    public ResponseEntity<Problem> handleIllegalArgument(OuracademyException ex, Locale locale) {
        String errorMessage = getLocalizedMessage(ex, locale);
        var problem = Problem.builder()
            .withDetail(errorMessage)
            .withTitle(ex.getTitle())
            .withType(ex.getType())
            .withStatus(ex.getStatus())
            .with("args", ex.getArgs())
            .build();

        return new ResponseEntity<>(problem, HttpStatus.valueOf(ex.getStatus().getStatusCode()));
    }

    @Override
    public ResponseEntity<Problem> handleAuthentication(AuthenticationException ex, NativeWebRequest request) {
        String errorMessage = messageSource.getMessage("unauthorized", null, LocaleContextHolder.getLocale());

        var problem = Problem.builder()
            .withDetail(errorMessage)
            .withTitle(ex.getMessage())
            .withStatus(Status.UNAUTHORIZED)
            .build();
            
        return new ResponseEntity<>(problem, HttpStatus.UNAUTHORIZED);
    }

    private String getLocalizedMessage(OuracademyException ex, Locale locale) {
        try {
            return messageSource.getMessage(ex.getCode(), ex.getArgs().values().toArray(), locale);
        } catch(NoSuchMessageException e) {
            return ex.getCode();
        }
    }
}
