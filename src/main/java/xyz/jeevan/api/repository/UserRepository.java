package xyz.jeevan.api.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xyz.jeevan.api.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

  /**
   * Fetch User by email ID.
   *
   * @param email Email ID.
   * @return {@code User} User object.
   */
  User getByEmail(String email);

  /**
   * Fetch user by email and organization ID.
   *
   * @param email Email ID.
   * @param organizationId Organization ID.
   * @return {@cod User} User object.
   */
  User getByEmailAndOrganizationId(String email, String organizationId);

  List<User> getByOrOrganizationIdAndActive(String organizationId, boolean active);
}
