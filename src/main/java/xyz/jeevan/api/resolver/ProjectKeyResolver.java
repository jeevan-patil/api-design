package xyz.jeevan.api.resolver;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;
import xyz.jeevan.api.key.ProjectKey;
import xyz.jeevan.api.utils.SecurityUtil;

/**
 * This is implementation of custom arguments which can be part of controller methods. This feature
 * can be used to create metadata from request data. This can be helpful to check resource or entity
 * level user access. For e.g. data of one organization should not be available to users of other
 * organizations.
 */
public class ProjectKeyResolver implements HandlerMethodArgumentResolver {

  String variableName = "projectId";

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterType().equals(ProjectKey.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    String userId = SecurityUtil.loggedInUser().getId();
    String projectId = "";

    HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
    Map<String, String> pathVariables = (Map<String, String>) httpServletRequest
        .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

    for (String param : pathVariables.keySet()) {
      if (variableName.equalsIgnoreCase(param)) {
        projectId = pathVariables.get(param);
        break;
      }
    }

    if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(projectId)) {
      throwMissingParameterException("projectKey", ProjectKey.class);
    }

    return new ProjectKey(projectId, userId);
  }

  protected void throwMissingParameterException(String paramName, Class paramType)
      throws Exception {
    throw new IllegalStateException(
        "Missing parameter '" + paramName + "' of type [" + paramType.getName() + "]");
  }
}
