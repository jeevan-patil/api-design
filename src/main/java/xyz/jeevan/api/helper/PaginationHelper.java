package xyz.jeevan.api.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import xyz.jeevan.api.exception.ApplicationException;
import xyz.jeevan.api.exception.ErrorResponseEnum;

/**
 * Created by jeevan on 15/7/17.
 */

@Component
public class PaginationHelper {

  @Value("${response.default.list.size}")
  private int defaultPageSize;

  @Value("${response.allowed.list.size}")
  private int maxPageSize;

  public int refinePageNumber(Integer page) {
    if (page == null || page < 0) {
      return 0;
    }
    return page;
  }

  public int validateResponseLimit(Integer limit) {
    if (limit == null || limit <= 0) {
      return defaultPageSize;
    } else if (limit > maxPageSize) {
      throw new ApplicationException(ErrorResponseEnum.PAGINATION_LIMIT_ERROR);
    }
    return limit;
  }
}
