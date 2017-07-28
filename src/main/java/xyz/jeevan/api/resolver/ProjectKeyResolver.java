package xyz.jeevan.api.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import xyz.jeevan.api.key.ProjectKey;

/**
 * This is implementation of custom arguments which can be part of controller methods. This feature
 * can be used to create metadata from request data. This can be helpful to check resource or entity
 * level user access.
 */
public class ProjectKeyResolver implements HandlerMethodArgumentResolver {

  private String userId = "5974c81791f1ad157e9ba284";

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterType().equals(ProjectKey.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    return new ProjectKey("5974c81791f1ad157e9ba284", userId);
  }
}
