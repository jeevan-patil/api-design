package xyz.jeevan.api.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.jeevan.api.annotation.LogExecutionTime;
import xyz.jeevan.api.domain.User;
import xyz.jeevan.api.repository.OrganizationRepository;
import xyz.jeevan.api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(UserServiceImpl.class);

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private OrganizationRepository organizationRepository;

  @Override
  public void create(User user) {
    //TODO do not forget to implement me
  }

  @Override
  @LogExecutionTime
  public User getById(String id) {
    return userRepository.findOne(id);
  }
}
