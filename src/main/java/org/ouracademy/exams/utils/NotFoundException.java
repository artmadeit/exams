package org.ouracademy.exams.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * A special illegal arguments exception...maybe this will deleted
 * 
 * Don't use this in examples like:
 * entity.findById(id).orElseThrow(() -> new NotFoundException(...))
 * 
 * instead use ResponseEntity.of(entity.findById(id))
 */
public class NotFoundException extends BadArgumentsException {

  public NotFoundException(String code, Object[] args) {
    super("not_found." + code, args);
  }

  /**
   * @return lowercase humanize from a
   * 
   *         from => to ExamEvent => exam event Person => person
   */
  public static String humanize(String className) {
    return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(className), ' ').toLowerCase();
  }
}