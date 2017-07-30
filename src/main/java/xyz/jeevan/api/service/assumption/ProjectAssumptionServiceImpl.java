package xyz.jeevan.api.service.assumption;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import xyz.jeevan.api.annotation.LogExecutionTime;
import xyz.jeevan.api.domain.Assumption;
import xyz.jeevan.api.domain.Project;
import xyz.jeevan.api.domain.ProjectAssumption;
import xyz.jeevan.api.domain.QProjectAssumption;
import xyz.jeevan.api.helper.PaginationHelper;
import xyz.jeevan.api.repository.AssumptionRepository;
import xyz.jeevan.api.repository.ProjectAssumptionRepository;
import xyz.jeevan.api.repository.ProjectRepository;
import xyz.jeevan.api.utils.DateUtil;

@Service
public class ProjectAssumptionServiceImpl implements ProjectAssumptionService {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(ProjectAssumptionServiceImpl.class);

  @Autowired
  private PaginationHelper paginationHelper;

  @Autowired
  private ProjectAssumptionRepository projectAssumptionRepository;

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private AssumptionRepository assumptionRepository;

  @Override
  @LogExecutionTime
  public List<ProjectAssumption> findByProject(String projectId, String sortBy, String sortDir) {
    Assert.notNull(projectId, "Project ID can not be null.");
    QProjectAssumption predicate = QProjectAssumption.projectAssumption;
    List<ProjectAssumption> projectAssumptions = Lists.newArrayList(projectAssumptionRepository
        .findAll(predicate.projectId.eq(projectId), paginationHelper.sort(sortBy, sortDir)));
    return projectAssumptions;
  }

  @Override
  @LogExecutionTime
  public void copyNewAssumptionInProjects(Assumption assumption) {
    LOG.info("Copy newly created assumption in all the projects of an organization.");
    Assert.notNull(assumption, "Assumption data can not be null.");

    List<Project> projects = projectRepository
        .findByOrganizationIdAndActive(assumption.getOrgId(), true);

    if (projects == null) {
      return;
    }

    ProjectAssumption projectAssumption = buildProjectAssumption(assumption);
    for (Project project : projects) {
      if (project.isActive()) {
        projectAssumption.setId(null);
        projectAssumption.setProjectId(project.getId());
        projectAssumptionRepository.save(projectAssumption);
      }
    }
  }

  @Override
  @LogExecutionTime
  public void createProjectAssumptions(Project project) {
    LOG.info("Create assumptions for newly created project.");
    Assert.notNull(project, "Project data can not be null.");
    Assert.notNull(project.getOrganizationId(),
        "Organization id not available to fetch assumptions.");

    List<Assumption> assumptions = assumptionRepository
        .findByOrgIdAndActive(project.getOrganizationId(), true);

    if (assumptions != null) {
      List<ProjectAssumption> projectAssumptions = new ArrayList<>();
      for (Assumption assumption : assumptions) {
        ProjectAssumption projectAssumption = buildProjectAssumption(assumption);
        projectAssumption.setProjectId(project.getId());
        projectAssumptions.add(projectAssumption);
      }
      projectAssumptionRepository.save(projectAssumptions);
    }
  }

  private ProjectAssumption buildProjectAssumption(Assumption assumption) {
    ProjectAssumption projectAssumption = new ProjectAssumption();
    projectAssumption.setCreatedAt(DateUtil.now());
    projectAssumption.setAssumptionId(assumption.getId());
    if (!StringUtils.isEmpty(assumption.getDefaultValue())) {
      projectAssumption.setValue(assumption.getDefaultValue());
    } else {
      projectAssumption.setValue(null);
    }
    projectAssumption.setUnit(null);
    return projectAssumption;
  }
}
