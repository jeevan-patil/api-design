package xyz.jeevan.api.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.jeevan.api.domain.ProjectAssumption;

public interface ProjectAssumptionRepository extends MongoRepository<ProjectAssumption, String> {

  List<ProjectAssumption> findByProjectId(String projectId);
}
