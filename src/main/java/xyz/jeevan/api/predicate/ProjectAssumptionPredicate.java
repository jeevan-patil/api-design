package xyz.jeevan.api.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import xyz.jeevan.api.domain.ProjectAssumption;
import xyz.jeevan.api.helper.SearchCriteriaBuilder;
import xyz.jeevan.api.key.SearchCriteria;

public final class ProjectAssumptionPredicate {

  public static BooleanExpression getPredicate(SearchCriteria searchCriteria) {
    PathBuilder<ProjectAssumption> entityPath = new PathBuilder<>(ProjectAssumption.class,
        "projectassumption");

    String value1 = String.valueOf(searchCriteria.getValue1());
    if (SearchCriteriaBuilder.isNumeric(searchCriteria.getOperation())) {
      NumberPath<Integer> path = entityPath.getNumber(searchCriteria.getKey(), Integer.class);
      int intVal1 = Integer.parseInt(value1);
      switch (searchCriteria.getOperation()) {
        case "<=":
          return path.loe(intVal1);
        case ">":
          return path.gt(intVal1);
        case "<":
          return path.lt(intVal1);
        case ">=":
          return path.goe(intVal1);
      }
    } else {
      StringPath path = entityPath.getString(searchCriteria.getKey());
      switch (searchCriteria.getOperation()) {
        case ":":
          return path.containsIgnoreCase(value1);
        case "=":
          return path.equalsIgnoreCase(value1);
        case "!=":
          return path.notEqualsIgnoreCase(value1);
      }
    }
    return null;
  }
}
