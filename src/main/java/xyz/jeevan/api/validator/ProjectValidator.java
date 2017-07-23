package xyz.jeevan.api.validator;

import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;
import xyz.jeevan.api.domain.Project;
import xyz.jeevan.api.exception.ValidationError;
import xyz.jeevan.api.exception.ValidationErrorType;
import xyz.jeevan.api.utils.AppConstants;

public class ProjectValidator {

  private ProjectValidator() {
  }

  public static List<ValidationError> validateProjectData(final Project project) {
    List<ValidationError> validationErrorList = new ArrayList<>();

    if (StringUtils.isEmpty(project.getName())) {
      validationErrorList.add(new ValidationError(AppConstants.NAME,
          ValidationErrorType.REQUIRED_FIELD_MISSING.getErrorType()));
    }

    if (StringUtils.isEmpty(project.getOrganizationId())) {
      validationErrorList.add(new ValidationError(AppConstants.ORGANIZATION_ID,
          ValidationErrorType.REQUIRED_FIELD_MISSING.getErrorType()));
    }

    return validationErrorList;
  }
}
