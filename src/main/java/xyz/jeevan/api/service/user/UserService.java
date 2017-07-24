package xyz.jeevan.api.service.user;

import xyz.jeevan.api.domain.User;

public interface UserService {

  /**
   * Service method to create new user.
   *
   * @param user User information object.
   */
  void create(User user);

  /**
   * Method to fetch user {@see User} by id.
   *
   * @param id User ID.
   * @return {@code User} User object.
   */
  User getById(String id);
}
