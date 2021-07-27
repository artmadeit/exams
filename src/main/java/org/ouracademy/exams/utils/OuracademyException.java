package org.ouracademy.exams.utils;

import java.net.URI;

import org.zalando.problem.StatusType;

import lombok.Getter;

// Used as a base exception object
// see Patterns of Enterprise Architecture & Exceptional Ruby
@Getter
public abstract class OuracademyException extends RuntimeException {
    
    public static final URI DEFAULT_TYPE = URI.create("https://our-academy.org/generic-exception");
    public static final String DEFAULT_TITLE = "Exception in ouracademy application";
  
    final String title;
    final URI type;
    final Object[] args;

    protected OuracademyException(String code, String title, URI type, Object[] args) {
        super(code);
        this.title = title;
        this.type = type;
        this.args = args;
    }

    protected OuracademyException(String code, String title, URI type) {
        this(code, title, type, new Object[] {}); 
    }

    public abstract StatusType getStatus();
}
