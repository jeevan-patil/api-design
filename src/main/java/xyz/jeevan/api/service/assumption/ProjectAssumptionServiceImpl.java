package xyz.jeevan.api.service.assumption;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import xyz.jeevan.api.annotation.LogExecutionTime;
import xyz.jeevan.api.domain.ProjectAssumption;
import xyz.jeevan.api.repository.ProjectAssumptionRepository;

@Service
public class ProjectAssumptionServiceImpl implements ProjectAssumptionService {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(ProjectAssumptionServiceImpl.class);

  @Autowired
  private ProjectAssumptionRepository projectAssumptionRepository;

  @Override
  @LogExecutionTime
  public List<ProjectAssumption> findByProject(String projectId) {
    Assert.notNull(projectId, "Project ID can not be null.");
    return projectAssumptionRepository.findByProjectId(projectId);
  }
}
