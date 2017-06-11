package xyz.jeevan.api.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeevan on 11/6/17.
 */
public final class FieldConstants {

  private FieldConstants() {
  }

  public static final String[] EXCLUDEALL_FIELDS = {"*"};
  public static final String ORGANIZATION = "Organization";
  private static String[] orgFields = {AppConstants.ID, AppConstants.NAME};

  public static final Map<String, String[]> defaultEntityFields = new HashMap<String, String[]>() {{
    put(ORGANIZATION, orgFields);
  }};
}
