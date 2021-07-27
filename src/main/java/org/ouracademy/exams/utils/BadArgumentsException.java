package org.ouracademy.exams.utils;

import java.net.URI;

public class BadArgumentsException extends OuracademyException {
  
  public static final URI TYPE = URI.create("https://our-academy.org/bad-request");
  
  public BadArgumentsException(String message) {
    super(message);
  }

  public BadArgumentsException(String message, Object[] args) {
    super(message, args);
  }

  @Override
  public String getTitle() {
    return "Bad request";
  }

  @Override
  public URI getType() {
      return TYPE;
  }
}