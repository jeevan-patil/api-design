package xyz.jeevan.api.helper;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import xyz.jeevan.api.exception.ApplicationException;
import xyz.jeevan.api.exception.ErrorResponseEnum;
import xyz.jeevan.api.utils.AppConstants;

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

  public PageRequest getPageRequest(int page, int limit, String sortBy, String sortDir) {
    Sort.Direction direction = ASC.toString().equalsIgnoreCase(sortDir) ? ASC : DESC;

    if (StringUtils.isEmpty(sortBy)) {
      sortBy = AppConstants.DEFAULT_SORT_BY;
    }

    return new PageRequest(page, limit, new Sort(new Sort.Order(direction, sortBy)));
  }
}
