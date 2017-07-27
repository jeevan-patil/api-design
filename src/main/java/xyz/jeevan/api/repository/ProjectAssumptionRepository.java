package xyz.jeevan.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.jeevan.api.domain.ProjectAssumption;

public interface ProjectAssumptionRepository extends MongoRepository<ProjectAssumption, String> {

  Long countByProjectId(String projectId);

  void deleteByProjectId(String projectId);
}
