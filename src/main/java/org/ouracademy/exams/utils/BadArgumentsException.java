package org.ouracademy.exams.utils;

import java.net.URI;

import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

public class BadArgumentsException extends OuracademyException {
  
  public static final URI ERROR_TYPE = URI.create("https://our-academy.org/bad-request");
  
  public BadArgumentsException(String message) {
    this(message, new Object[] {});
  }

  public BadArgumentsException(String message, Object[] args) {
    super(message, "Bad request", ERROR_TYPE, args);
  }

  @Override
  public StatusType getStatus() {
    return Status.BAD_REQUEST;
  }
}