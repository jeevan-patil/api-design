package xyz.jeevan.api.key;

import xyz.jeevan.api.helper.SearchCriteriaBuilder;

public class SearchCriteria {

  private String key;
  private String operation;
  private Object value1;
  private Object value2;

  public SearchCriteria(String key, String operation, Object value1, Object value2) {
    this.key = key;
    this.operation = operation;
    this.value1 = value1;
    this.value2 = value2;
  }

  public String getKey() {
    return key;
  }

  public String getOperation() {
    return operation;
  }

  public Object getValue1() {
    return value1;
  }

  public Object getValue2() {
    return value2;
  }

  @Override
  public String toString() {
    return "SearchCriteria { " + key + " " + SearchCriteriaBuilder.searchrules.get(operation) + " "
        + value1 + (value2 != null ? " and " + value2 : "") + " }";
  }
}
