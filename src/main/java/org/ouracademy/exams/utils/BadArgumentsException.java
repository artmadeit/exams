package org.ouracademy.exams.utils;

import java.net.URI;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class BadArgumentsException extends AbstractThrowableProblem {

    private static final URI TYPE
      = URI.create("https://our-academy.org/bad-request");

    public BadArgumentsException(String detail) {
        super(
          TYPE,
          "Illegal arguments exception",
          Status.BAD_REQUEST,
          detail);
    }

}