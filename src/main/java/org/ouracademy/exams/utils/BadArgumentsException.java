package org.ouracademy.exams.utils;

import java.net.URI;

import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

public class BadArgumentsException extends OuracademyException {
  
  public static final URI ERROR_TYPE = URI.create("https://our-academy.org/bad-request");
  
  public BadArgumentsException(String code) {
    this(code, new Object[] {});
  }

  public BadArgumentsException(String code, Object[] args) {
    super(code, "Bad request", ERROR_TYPE, args);
  }

  @Override
  public StatusType getStatus() {
    // Reason why not 400 bad arguments: https://stackoverflow.com/questions/16133923/400-vs-422-response-to-post-of-data
    return Status.UNPROCESSABLE_ENTITY;
  }
}