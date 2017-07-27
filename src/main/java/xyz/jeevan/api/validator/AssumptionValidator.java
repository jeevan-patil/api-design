package xyz.jeevan.api.validator;

import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;
import xyz.jeevan.api.domain.Assumption;
import xyz.jeevan.api.exception.ValidationError;
import xyz.jeevan.api.exception.ValidationErrorType;
import xyz.jeevan.api.utils.AppConstants;

public final class AssumptionValidator {

  private AssumptionValidator() {
  }

  public static List<ValidationError> validateAssumption(final Assumption assumption) {
    List<ValidationError> validationErrorList = new ArrayList<>();

    if (StringUtils.isEmpty(assumption.getLabel())) {
      validationErrorList.add(new ValidationError("label",
          ValidationErrorType.REQUIRED_FIELD_MISSING.getErrorType()));
    }

    if (StringUtils.isEmpty(assumption.getOrgId())) {
      validationErrorList.add(new ValidationError("orgId",
          ValidationErrorType.REQUIRED_FIELD_MISSING.getErrorType()));
    }

    if (StringUtils.isEmpty(assumption.getFieldType())) {
      validationErrorList.add(new ValidationError("fieldType",
          ValidationErrorType.REQUIRED_FIELD_MISSING.getErrorType()));
    } else if (!AppConstants.fieldTypes.contains(assumption.getFieldType())) {
      validationErrorList.add(new ValidationError("fieldType",
          "Field type should be one of these " + String.join(",", AppConstants.fieldTypes)));
    }

    if (StringUtils.isEmpty(assumption.getReferenceName())) {
      validationErrorList.add(new ValidationError("referenceName",
          ValidationErrorType.REQUIRED_FIELD_MISSING.getErrorType()));
    }
    return validationErrorList;
  }
}
