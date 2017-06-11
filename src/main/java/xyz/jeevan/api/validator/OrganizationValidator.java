package xyz.jeevan.api.validator;

import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;
import xyz.jeevan.api.domain.Organization;
import xyz.jeevan.api.exception.ValidationError;
import xyz.jeevan.api.exception.ValidationErrorType;
import xyz.jeevan.api.utils.AppConstants;

/**
 * Created by jeevan on 10/6/17.
 */
public final class OrganizationValidator {

  private OrganizationValidator() {
  }

  public static List<ValidationError> validateOrganizationData(final Organization organization) {
    List<ValidationError> validationErrorList = new ArrayList<>();

    if (StringUtils.isEmpty(organization.getName())) {
      validationErrorList.add(new ValidationError(AppConstants.NAME,
          ValidationErrorType.REQUIRED_FIELD_MISSING.getErrorType()));
    }

    if (StringUtils.isEmpty(organization.getContact())) {
      validationErrorList.add(new ValidationError(AppConstants.CONTACT,
          ValidationErrorType.REQUIRED_FIELD_MISSING.getErrorType()));
    }

    if (StringUtils.isEmpty(organization.getEmail())) {
      validationErrorList.add(new ValidationError(AppConstants.EMAIL,
          ValidationErrorType.REQUIRED_FIELD_MISSING.getErrorType()));
    }
    return validationErrorList;
  }
}
