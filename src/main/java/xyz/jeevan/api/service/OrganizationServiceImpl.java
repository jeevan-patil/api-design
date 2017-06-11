package xyz.jeevan.api.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.jeevan.api.domain.Organization;
import xyz.jeevan.api.exception.ErrorResponseEnum;
import xyz.jeevan.api.exception.ValidationError;
import xyz.jeevan.api.exception.ValidationException;
import xyz.jeevan.api.repository.OrganizationRepository;
import xyz.jeevan.api.utils.DateUtil;
import xyz.jeevan.api.utils.UniqueIdGenerator;
import xyz.jeevan.api.validator.OrganizationValidator;

/**
 * Created by jeevan on 10/6/17.
 */

@Service
public class OrganizationServiceImpl implements OrganizationService {

  @Autowired
  private OrganizationRepository orgRepo;

  @Override
  public Organization save(Organization org) {
    List<ValidationError> validationErrorList = OrganizationValidator.validateOrganizationData(org);
    if (!validationErrorList.isEmpty()) {
      throw new ValidationException(validationErrorList, ErrorResponseEnum.VALIDATION_ERROR);
    }

    org.setId(UniqueIdGenerator.generate());
    org.setActive(true);
    org.setCreatedAt(DateUtil.now());
    org.setUpdatedAt(DateUtil.now());
    return orgRepo.save(org);
  }
}
