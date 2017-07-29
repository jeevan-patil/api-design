package xyz.jeevan.api.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import xyz.jeevan.api.domain.Project;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String>,
    QueryDslPredicateExecutor<Project> {

  /**
   * Find project by name and organization.
   *
   * @param name Project name. Search will be case insensitive.
   * @param organizationId Organization ID.
   * @return {@code Project} project object.
   */
  @Query(value = "{'name': {$regex : '^?0$', $options: 'i'}}")
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
