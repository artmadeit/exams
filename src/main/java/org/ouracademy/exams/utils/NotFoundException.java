package org.ouracademy.exams.utils;

import java.net.URI;

import org.apache.commons.lang3.StringUtils;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

public class NotFoundException extends OuracademyException {

  private static final URI ERROR_TYPE = URI.create("https://our-academy.org/not-found");

  public NotFoundException(@SuppressWarnings("rawtypes") Class entity, Long id) {
    this("entity_by_id", new Object[] { humanize(entity.getSimpleName()), id });
  }

  public NotFoundException(String code, Object[] args) {
    super("not_found." + code, "Not found", ERROR_TYPE, args);
  }

  @Override
  public StatusType getStatus() {
    return Status.NOT_FOUND;
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