package xyz.jeevan.api.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.jeevan.api.domain.User;
import xyz.jeevan.api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(UserServiceImpl.class);

  @Autowired
  private UserRepository userRepository;

  @Override
  public void create(User user) {
    userRepository.save(user);
  }
}
