package xyz.jeevan.api.service.assumption;

import java.util.List;
import xyz.jeevan.api.domain.ProjectAssumption;

public interface ProjectAssumptionService {

  List<ProjectAssumption> findByProject(String projectId);
}
