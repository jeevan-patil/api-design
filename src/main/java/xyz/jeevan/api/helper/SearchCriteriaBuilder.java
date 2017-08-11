package xyz.jeevan.api.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import xyz.jeevan.api.key.SearchCriteria;

public final class SearchCriteriaBuilder {

  public static final Map<String, String> searchRules = new HashMap<String, String>() {{
    put("=", "equals");
    put(":", "like");
    put("<", "less than");
    put(">", "greater than");
    put("<=", "less than equals");
    put(">=", "greater than equals");
    put("!=", "not equals");
  }};
  private static final String[] numericOps = {"<", ">", "<=", ">="};
  private static final String SYMBOL_SEPARATOR = "|";

  public static List<SearchCriteria> build(String criteria) {
    List<SearchCriteria> criterion = new ArrayList<>();
    String ruleRegex = StringUtils.join(searchRules.keySet(), SYMBOL_SEPARATOR);

    Pattern pattern = Pattern.compile("(\\w+?)(" + ruleRegex + ")(\\w+?),");
    Matcher matcher = pattern.matcher(criteria + ",");
    while (matcher.find()) {
      criterion.add(new SearchCriteria(matcher.group(1),
          matcher.group(2), matcher.group(3),
          (matcher.groupCount() > 3 ? matcher.group(4) : null)));
    }

    return criterion;
  }

  public static boolean isNumeric(String operation) {
    return Arrays.asList(numericOps).contains(operation);
  }

  public static void main(String a[]) {
    List<SearchCriteria> criterion = build(
        "lastName=doe,age>25,first:patil,height<=6,city=null,state!=MH,asddf<>234324__234433");
    for (SearchCriteria criteria : criterion) {
      System.out.println(criteria);
    }
  }
}
