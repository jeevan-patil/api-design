package xyz.jeevan.api.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import xyz.jeevan.api.key.ProjectKey;

public class ProjectKeyResolver implements HandlerMethodArgumentResolver {

  private String userId = "5974c81791f1ad157e9ba284";

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterType().equals(ProjectKey.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    System.out.println("In resolver");
    return new ProjectKey("5974c81791f1ad157e9ba284", userId);
  }
}
