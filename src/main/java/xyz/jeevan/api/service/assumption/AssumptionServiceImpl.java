package xyz.jeevan.api.service.assumption;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import xyz.jeevan.api.annotation.LogExecutionTime;
import xyz.jeevan.api.domain.Assumption;
import xyz.jeevan.api.helper.PaginationHelper;
import xyz.jeevan.api.repository.AssumptionRepository;

@Service
public class AssumptionServiceImpl implements AssumptionService {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(AssumptionServiceImpl.class);

  @Value("${pagination.assumption.default}")
  private int defaultPageSize;

  @Value("${pagination.assumption.max}")
  private int maxPageSize;

  @Autowired
  private PaginationHelper paginationHelper;

  @Autowired
  private AssumptionRepository assumptionRepository;

  @Override
  @LogExecutionTime
  public List<Assumption> getOrganizationAssumptions(String orgId, Integer page, Integer limit) {
    page = paginationHelper.refinePageNumber(page);
    limit = paginationHelper.validateResponseLimit(limit, defaultPageSize, maxPageSize);
    LOG.info("Get assumptions for organization {}, page {}, limit {}", orgId, page, limit);

    Page<Assumption> assumptions = assumptionRepository
        .findByAndOrgIdAndActive(orgId, true, new PageRequest(page, limit));
    return assumptions.getContent();
  }
}
