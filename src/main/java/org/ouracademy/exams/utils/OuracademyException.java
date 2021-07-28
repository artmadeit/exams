package org.ouracademy.exams.utils;

import java.net.URI;

import org.zalando.problem.StatusType;

import lombok.Getter;
import lombok.ToString;

// Used as a base exception object
// see Patterns of Enterprise Architecture & Exceptional Ruby
@Getter
@ToString
public abstract class OuracademyException extends RuntimeException {
    
    public static final URI DEFAULT_TYPE = URI.create("https://our-academy.org/generic-exception");
    public static final String DEFAULT_TITLE = "Exception in ouracademy application";
  
    final String title;
    final URI type;
    final Object[] args;
    final String code;

    protected OuracademyException(String code, String title, URI type, Object[] args) {
        this.code = code;
        this.title = title;
        this.type = type;
        this.args = args;
    }

    protected OuracademyException(String code, String title, URI type) {
        this(code, title, type, new Object[] {}); 
    }

    public abstract StatusType getStatus();

    @Override
    public String getMessage() {
        return toString();
    }
}
