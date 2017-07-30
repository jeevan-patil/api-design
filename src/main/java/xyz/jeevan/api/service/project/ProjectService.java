package xyz.jeevan.api.service.project;

import com.querydsl.core.types.Predicate;
import java.util.List;
import xyz.jeevan.api.domain.Project;

public interface ProjectService {

  /**
   * Service method to create new project.
   *
   * @param project Project information object.
   */
  void create(Project project);

  /**
   * Find project by id.
   *
   * @param id project id.
   * @return {@code Project} project information object.
   */
  Project getById(String id);

  /**
   * Check if user has access to the project.
   *
   * @param projectId Project ID.
   * @param userId User ID.
   * @return True if user has access to the project, false otherwise.
   */
  boolean checkProjectUserAccess(String projectId, String userId);

  /**
   * Project Search method.
   *
   * @param orgId Organization ID..
   * @param page Page number.
   * @param limit Page limit.
   * @param sortBy Sort result list by column.
   * @param sortDir Sort result list by direction.
   * @return {@code List<Project>} Project list.
   */
  List<Project> search(String orgId, Integer page, Integer limit, String sortBy,
      String sortDir);
}
