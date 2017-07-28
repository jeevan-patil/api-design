package xyz.jeevan.api.service.assumption;

import java.util.List;
import xyz.jeevan.api.domain.ProjectAssumption;

public interface ProjectAssumptionService {

  /**
   * Find project assumptions.
   *
   * @param projectId Project ID.
   * @return {@code List<ProjectAssumption>} List of project assumptions.
   */
  List<ProjectAssumption> findByProject(String projectId);
}
