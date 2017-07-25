package xyz.jeevan.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xyz.jeevan.api.domain.ProjectUser;

@Repository
public interface ProjectUserRepository extends MongoRepository<ProjectUser, String> {

  ProjectUser getByProjectIdAndUserIdAndActive(String projectId, String userId, boolean active);
}
