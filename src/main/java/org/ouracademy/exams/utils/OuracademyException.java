package org.ouracademy.exams.utils;

import java.net.URI;

import org.zalando.problem.StatusType;

import lombok.Getter;

// Used as a base exception object
// see Patterns of Enterprise Architecture & Exceptional Ruby
public abstract class OuracademyException extends RuntimeException {
    
    public static final URI TYPE = URI.create("https://our-academy.org/generic-exception");
    public static final String TITLE = "Exception in ouracademy application";
  
    @Getter
    final Object[] args;

    protected OuracademyException(String message) {
        this(message,  new Object[] {});
    }

    protected OuracademyException(String message, Object[] args) {
        super(message);
        this.args = args;
    }

    public String getTitle() {
        return TITLE;
    }
    
    public URI getType() {
        return TYPE;
    }

    public abstract StatusType getStatus();
}
