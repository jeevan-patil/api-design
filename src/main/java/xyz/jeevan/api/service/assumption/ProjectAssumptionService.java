package xyz.jeevan.api.service.assumption;

import java.util.List;
import xyz.jeevan.api.domain.Assumption;
import xyz.jeevan.api.domain.Project;
import xyz.jeevan.api.domain.ProjectAssumption;

public interface ProjectAssumptionService {

  /**
   * Find project assumptions.
   *
   * @param projectId Project ID.
   * @param criteria Search criteria. e.g. lastName=doe,age>25
   * @param sortBy Sort by property.
   * @param sortDir Sort direction.
   * @return {@code List<ProjectAssumption>} List of project assumptions.
   */
  List<ProjectAssumption> search(String projectId, String criteria, String sortBy, String sortDir);

  /**
   * Whenever a new assumption is created in an organization, copy that assumption in all the
   * existing projects under the organization.
   *
   * @param assumption Assumption object.
   */
  void copyNewAssumptionInProjects(Assumption assumption);

  /**
   * Create project assumptions for newly created project.
   *
   * @param project {@link Project} Project object.
   */
  void createProjectAssumptions(Project project);
}
