package xyz.jeevan.api.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.jeevan.api.domain.User;
import xyz.jeevan.api.exception.ApplicationException;
import xyz.jeevan.api.repository.UserRepository;

/**
 * @author jeevan
 */
@Service("userDetailsService")
public class UserDetailService implements UserDetailsService {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(UserDetailService.class);

  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    LOG.info("Authenticate user {}", username);
    User user;

    try {
      user = userRepository.findByEmail(username);
    } catch (Exception repositoryProblem) {
      throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(),
          repositoryProblem);
    }

    if (user == null) {
      LOG.error("User not found with username {}", username);
      throw new InternalAuthenticationServiceException(
          "Wrong credentials. Please try again.");
    }

    if (user.isLocked()) {
      LOG.error("User account is locked. username {}", username);
      throw new ApplicationException("Account is locked.");
    }

    if (!user.isActive()) {
      LOG.error("User account is not active. username {}", username);
      throw new ApplicationException("User is not active.");
    }

    return user;
  }

  @Autowired
  public void setUserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
}
