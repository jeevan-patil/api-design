package xyz.jeevan.api.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.jeevan.api.domain.ProjectAssumption;

public interface ProjectAssumptionRepository extends MongoRepository<ProjectAssumption, String> {

  /**
   * Find project assumptions.
   *
   * @param projectId Project ID.
   * @return {@code List<ProjectAssumption>} List of project assumptions.
   */
  List<ProjectAssumption> findByProjectId(String projectId);
}
