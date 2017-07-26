package xyz.jeevan.api.service.organization;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import xyz.jeevan.api.annotation.LogExecutionTime;
import xyz.jeevan.api.domain.Organization;
import xyz.jeevan.api.exception.ApplicationException;
import xyz.jeevan.api.exception.ErrorResponseEnum;
import xyz.jeevan.api.exception.ValidationError;
import xyz.jeevan.api.exception.ValidationException;
import xyz.jeevan.api.helper.PaginationHelper;
import xyz.jeevan.api.repository.OrganizationRepository;
import xyz.jeevan.api.utils.DateUtil;
import xyz.jeevan.api.validator.OrganizationValidator;

/**
 * Created by jeevan on 10/6/17.
 */

@Service
public class OrganizationServiceImpl implements OrganizationService {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(OrganizationServiceImpl.class);

  @Autowired
  private OrganizationRepository orgRepo;

  @Autowired
  private PaginationHelper paginationHelper;

  @Value("${pagination.organization.default}")
  private int defaultPageSize;

  @Value("${pagination.organization.max}")
  private int maxPageSize;

  @Override
  @LogExecutionTime
  public Organization save(Organization org) {
    List<ValidationError> validationErrorList = OrganizationValidator.validateOrganizationData(org);
    if (!validationErrorList.isEmpty()) {
      LOG.error("Could not create an organization due to insufficient data.");
      throw new ValidationException(validationErrorList, ErrorResponseEnum.VALIDATION_ERROR);
    }

    Organization existing = orgRepo.findByName(org.getName());
    if (existing != null) {
      throw new ApplicationException(ErrorResponseEnum.ENTITY_EXISTS);
    }

    //org.setId(UniqueIdGenerator.generate());
    org.setActive(true);
    org.setCreatedAt(DateUtil.now());
    org.setUpdatedAt(DateUtil.now());
    return orgRepo.save(org);
  }

  @Override
  @LogExecutionTime
  public Organization getById(String id) {
    Assert.notNull(id, "Organization id can not be empty.");

    Organization org = orgRepo.findOne(id);
    if (org == null) {
      throw new ApplicationException(ErrorResponseEnum.ENTITY_NOT_FOUND);
    }

    return org;
  }

  @Override
  @LogExecutionTime
  public void delete(String id) {
    Assert.notNull(id, "Organization id can not be empty.");

    Organization org = orgRepo.findOne(id);
    if (org == null) {
      throw new ApplicationException(ErrorResponseEnum.ENTITY_NOT_FOUND);
    }

    orgRepo.delete(id);
  }

  @Override
  @LogExecutionTime
  public List<Organization> list(Integer page, Integer limit) {
    page = paginationHelper.refinePageNumber(page);
    limit = paginationHelper.validateResponseLimit(limit, defaultPageSize, maxPageSize);

    Page<Organization> organizations = orgRepo.findAll(new PageRequest(page, limit));
    return organizations.getContent();
  }
}
