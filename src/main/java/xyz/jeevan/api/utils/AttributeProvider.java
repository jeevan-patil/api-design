package xyz.jeevan.api.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.util.StringUtils;
import xyz.jeevan.api.annotation.DefaultField;

/**
 * Created by jeevan on 11/6/17.
 */
public final class AttributeProvider {

  private AttributeProvider() {
  }

  public static String[] provideFields(Class clazz, final String fields) {
    Set<String> finalFields = new HashSet<>();
    Field[] defaultFields = FieldUtils.getFieldsWithAnnotation(clazz, DefaultField.class);
    if (defaultFields != null) {
      for (Field field : defaultFields) {
        finalFields.add(field.getName());
      }
    }

    if (!StringUtils.isEmpty(fields)) {
      finalFields.addAll(Arrays.asList(fields.split(",")));
    }

    return finalFields.toArray(new String[finalFields.size()]);
  }
}
