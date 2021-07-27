package org.ouracademy.exams.utils;

import java.net.URI;

import lombok.Getter;

public class BadArgumentsException extends RuntimeException {
  @Getter
  final Object[] violations;
  
  public static final URI TYPE = URI.create("https://our-academy.org/bad-request");
  
  public BadArgumentsException(String message) {
    super(message);
    this.violations = new Object[] {};
  }

  public BadArgumentsException(String message, Object[] args) {
    super(message);
    this.violations = args;
  }
}