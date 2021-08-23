package org.ouracademy.exams.utils;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * A special illegal arguments exception
 * 
 * 
 * Never use this in GET
 * Use in POST / PUT when validating request body or path variables, example:
 * 
 * class SectionRequest { Long parentId }
 * 
 * // validating parentId exist in DB
 * var parent = examPartRepository.findById(sectionRequest.getParentId()).orElseThrow(() -> new NotFoundException())
 * var section = ExamPart.section("Section I: Soft skills", parent)
 * 
 */
public class NotFoundException extends BadArgumentsException {
  
  public NotFoundException(@SuppressWarnings("rawtypes") Class entity, Long id) {
    this("entity_by_id", Map.of("entity", toUnderscore(entity.getSimpleName()), "id", id ));
  }

  public NotFoundException(String code, Map<String, Object> args) {
    super("not_found." + code, args);
  }
  
  public NotFoundException(String code) {
    this(code, Collections.emptyMap());
  }

  public NotFoundException() {
    this("entity");
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