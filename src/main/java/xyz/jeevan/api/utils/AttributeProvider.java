package xyz.jeevan.api.utils;

import org.springframework.util.StringUtils;

/**
 * Created by jeevan on 11/6/17.
 */
public final class AttributeProvider {

  private AttributeProvider() {
  }

  public static String[] provideFields(final String entity, final String fields) {
    // if fields is * or empty, return only default fields
    if (AppConstants.ASTERISK.equals(fields) || StringUtils.isEmpty(fields)) {
      return FieldConstants.defaultEntityFields.get(entity);
    }

    return fields.split(",");
  }
}
