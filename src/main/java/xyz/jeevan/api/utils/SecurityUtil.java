package xyz.jeevan.api.utils;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import xyz.jeevan.api.domain.User;
import xyz.jeevan.api.exception.ApplicationException;

/**
 * @author jeevan
 */
public class SecurityUtil {

  public static User loggedInUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication instanceof AnonymousAuthenticationToken) {
      throw new ApplicationException("No user session available.");
    }

    return (User) authentication.getPrincipal();
  }

  public static String loggedInUserEmail() {
    return loggedInUser().getEmail();
  }
}
