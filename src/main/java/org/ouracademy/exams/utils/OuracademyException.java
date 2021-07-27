package org.ouracademy.exams.utils;

import java.net.URI;

import lombok.Getter;

// Used as a base exception object
// see Patterns of Enterprise Architecture & Exceptional Ruby
public class OuracademyException extends RuntimeException {
    
    public static final URI TYPE = URI.create("https://our-academy.org/generic-exception");
    public static final String TITLE = "Exception in ouracademy application";
  
    @Getter
    final Object[] args;

    public OuracademyException(String message) {
        this(message,  new Object[] {});
    }

    public OuracademyException(String message, Object[] args) {
        super(message);
        this.args = args;
    }

    public String getTitle() {
        return TITLE;
    }
    
    public URI getType() {
        return TYPE;
    }
}
