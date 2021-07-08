package org.ouracademy.exams.utils;

import java.net.URI;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class NotFoundException extends AbstractThrowableProblem {

    private static final URI TYPE
      = URI.create("https://our-academy.org/not-found");

    public NotFoundException(Class entity, Long id) {
        this("Not found " + entity.getName().toLowerCase() + " with id:" + id);
    }

    public NotFoundException(String detail) {
      super(
          TYPE,
          "Not found",
          Status.NOT_FOUND,
          detail);
    }

}