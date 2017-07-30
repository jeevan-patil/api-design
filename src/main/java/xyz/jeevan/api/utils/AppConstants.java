package xyz.jeevan.api.utils;

import java.util.ArrayList;
import java.util.List;

public final class AppConstants {

  public static final String ID = "id";
  public static final String NAME = "name";
  public static final String SUCCESS = "Success";
  public static final String EMPTY_REQUEST = "empty_request";
  public static final String STATUS = "status";
  public static final String CONTACT = "contact";
  public static final String EMAIL = "email";
  public static final String ASTERISK = "*";
  public static final String FIELDS = "fields";
  public static final String PAGE = "page";
  public static final String LIMIT = "limit";
  public static final String SORT_DIR = "sortDir";
  public static final String SORT_BY = "sortBy";
  public static final String DEFAULT_SORT_BY = "id";
  public static final String ORGANIZATION_ID = "organizationId";

  public static final List<String> fieldTypes = new ArrayList<String>() {{
    add("userlist");
    add("select");
    add("multiselect");
    add("numeric");
    add("text");
    add("textarea");
    add("date");
    add("yesno");
    add("currency");
  }};
}
