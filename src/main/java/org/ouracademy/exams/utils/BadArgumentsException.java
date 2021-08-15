package org.ouracademy.exams.utils;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

public class BadArgumentsException extends OuracademyException {
  
  public static final URI ERROR_TYPE = URI.create("https://our-academy.org/bad-arguments");
  
  public BadArgumentsException(String code) {
    this(code, Collections.emptyMap());
  }

  public BadArgumentsException(String code, Map<String, Object> args) {
    this(code, "Bad arguments", ERROR_TYPE, args);
  }

  public BadArgumentsException(String code, String title, URI type) {
    this(code, title, type, Collections.emptyMap());
  }

  public BadArgumentsException(String code, String title, URI type, Map<String, Object> args) {
    super(code, title, type, args);
  }

  @Override
  public StatusType getStatus() {
    // Reason why not 400 bad arguments: https://stackoverflow.com/questions/16133923/400-vs-422-response-to-post-of-data
    return Status.UNPROCESSABLE_ENTITY;
  }
}