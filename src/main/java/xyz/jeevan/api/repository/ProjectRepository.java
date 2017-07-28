package xyz.jeevan.api.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xyz.jeevan.api.domain.Project;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

  /**
   * Find project by name and organization.
   *
   * @param name Project name.
   * @param organizationId Organization ID.
   * @return {@code Project} project object.
   */
  Project findProjectByNameAndOrganizationId(String name, String organizationId);

  /**
   * Find project by organization and active indicator.
   *
   * @param organizationId Organization ID.
   * @param active Project status indicator.
   * @return {@code List<Project>} List of projects.
   */
  List<Project> findByOrganizationIdAndActive(String organizationId, boolean active);
}
