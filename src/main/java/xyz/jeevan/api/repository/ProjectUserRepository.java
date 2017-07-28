package xyz.jeevan.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xyz.jeevan.api.domain.ProjectUser;

@Repository
public interface ProjectUserRepository extends MongoRepository<ProjectUser, String> {

  /**
   * Fetch project user.
   *
   * @param projectId Project ID.
   * @param userId User ID.
   * @param active Project status indicator.
   * @return {@code ProjectUser} Project user object.
   */
  ProjectUser findByProjectIdAndUserIdAndActive(String projectId, String userId, boolean active);
}
