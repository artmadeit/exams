package org.ouracademy.exams.utils;

import java.util.Collections;

import org.apache.commons.lang3.StringUtils;

/**
 * A special illegal arguments exception
 * 
 * 
 * Never use this in GET
 * Use in POST / PUT when validating request body or path variables
 * 
 */
public class NotFoundException extends BadArgumentsException {
  
  public NotFoundException(@SuppressWarnings("rawtypes") Class entity) {
    this(toUnderscore(entity.getSimpleName()));
  }

  public NotFoundException(String code) {
    super("not_found." + code, Collections.emptyMap());
  }

  /**
   * @return lowercase humanize from a
   * 
   *         from => to ExamEvent => exam event Person => person
   */
  public static String toUnderscore(String className) {
    return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(className), '_').toLowerCase();
  }
}