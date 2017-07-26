package xyz.jeevan.api.helper;

import org.springframework.stereotype.Component;
import xyz.jeevan.api.exception.ApplicationException;
import xyz.jeevan.api.exception.ErrorResponseEnum;

/**
 * Created by jeevan on 15/7/17.
 */

@Component
public class PaginationHelper {

  public int refinePageNumber(Integer page) {
    if (page == null || page < 0) {
      return 0;
    }
    return page;
  }

  public int validateResponseLimit(Integer limit, Integer defaultPageSize, Integer maxPageSize) {
    if (limit == null || limit <= 0) {
      return defaultPageSize;
    } else if (limit > maxPageSize) {
      throw new ApplicationException(ErrorResponseEnum.PAGINATION_LIMIT_ERROR);
    }
    return limit;
  }
}
