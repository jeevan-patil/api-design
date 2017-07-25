package xyz.jeevan.api.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xyz.jeevan.api.domain.Project;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

  Project findProjectByNameAndOrganizationId(String name, String organizationId);

  List<Project> findByOrganizationIdAndActive(String organizationId, boolean active);
}
