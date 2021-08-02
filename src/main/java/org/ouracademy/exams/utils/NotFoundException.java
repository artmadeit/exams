package org.ouracademy.exams.utils;

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
    this("entity_by_id", new Object[] { toUnderscore(entity.getSimpleName()), id });
  }

  public NotFoundException(String code, Object[] args) {
    super("not_found." + code, args);
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