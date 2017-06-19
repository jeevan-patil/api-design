package xyz.jeevan.api.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import xyz.jeevan.api.domain.Organization;
import xyz.jeevan.api.exception.ApplicationException;
import xyz.jeevan.api.exception.ErrorResponseEnum;
import xyz.jeevan.api.exception.ValidationError;
import xyz.jeevan.api.exception.ValidationErrorType;
import xyz.jeevan.api.exception.ValidationException;
import xyz.jeevan.api.repository.OrganizationRepository;
import xyz.jeevan.api.utils.AppConstants;
import xyz.jeevan.api.utils.DateUtil;
import xyz.jeevan.api.utils.UniqueIdGenerator;
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

  @Override
  public Organization save(Organization org) {
    List<ValidationError> validationErrorList = OrganizationValidator.validateOrganizationData(org);
    if (!validationErrorList.isEmpty()) {
      LOG.error("Could not create an organization due to insufficient data.");
      throw new ValidationException(validationErrorList, ErrorResponseEnum.VALIDATION_ERROR);
    }

    Organization existing = orgRepo.findByName(org.getName());
    if(existing != null) {
      throw new ApplicationException(ErrorResponseEnum.ENTITY_EXISTS);
    }

    org.setId(UniqueIdGenerator.generate());
    org.setActive(true);
    org.setCreatedAt(DateUtil.now());
    org.setUpdatedAt(DateUtil.now());
    return orgRepo.save(org);
  }

  @Override
  public Organization getById(String id) {
    Assert.notNull(id, "Organization id can not be empty.");

    Organization org = orgRepo.findOne(id);
    if (org == null) {
      throw new ApplicationException(ErrorResponseEnum.ENTITY_NOT_FOUND);
    }

    return org;
  }
}
