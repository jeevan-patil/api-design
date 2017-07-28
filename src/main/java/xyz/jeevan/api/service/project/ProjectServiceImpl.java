package xyz.jeevan.api.service.project;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import xyz.jeevan.api.annotation.LogExecutionTime;
import xyz.jeevan.api.domain.Organization;
import xyz.jeevan.api.domain.Project;
import xyz.jeevan.api.domain.ProjectUser;
import xyz.jeevan.api.exception.ApplicationException;
import xyz.jeevan.api.exception.ErrorResponseEnum;
import xyz.jeevan.api.exception.ValidationError;
import xyz.jeevan.api.exception.ValidationException;
import xyz.jeevan.api.repository.OrganizationRepository;
import xyz.jeevan.api.repository.ProjectRepository;
import xyz.jeevan.api.repository.ProjectUserRepository;
import xyz.jeevan.api.utils.DateUtil;
import xyz.jeevan.api.validator.ProjectValidator;

@Service
public class ProjectServiceImpl implements ProjectService {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(ProjectServiceImpl.class);

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private OrganizationRepository organizationRepository;

  @Autowired
  private ProjectUserRepository projectUserRepository;

  @Override
  @LogExecutionTime
  public void create(Project project) {
    Assert.notNull(project, "Project data can not be null.");

    List<ValidationError> validationErrorList = ProjectValidator.validateProjectData(project);
    if (!validationErrorList.isEmpty()) {
      LOG.error("Could not create an project due to insufficient data.");
      throw new ValidationException(validationErrorList, ErrorResponseEnum.VALIDATION_ERROR);
    }

    String organizationId = project.getOrganizationId();
    String projectName = project.getName();

    Organization organization = organizationRepository.findOne(project.getOrganizationId());
    if (organization == null || !organization.isActive()) {
      LOG.error("Organization is inactive or not found. Could not create project {} in org {}",
          projectName, organizationId);
      throw new ApplicationException(ErrorResponseEnum.ORGANIZATION_INACTIVE_ERROR);
    }

    Project existingProject = projectRepository
        .findProjectByNameAndOrganizationId(project.getName(), project.getOrganizationId());
    if (existingProject != null) {
      LOG.error("Project with name {} already exists in organization {}. Specify another name.",
          projectName, organization.getName());
      throw new ApplicationException("Project with same name exists in the organization.");
    }

    project.setCreatedAt(DateUtil.now());
    projectRepository.save(project);
    LOG.info("Created project {} successfully.", projectName);
  }

  @Override
  @LogExecutionTime
  public Project getById(String id) {
    Assert.notNull(id, "Project id can not be null.");
    Project project = projectRepository.findOne(id);
    return project;
  }

  @Override
  @LogExecutionTime
  public boolean checkProjectUserAccess(String projectId, String userId) {
    Assert.notNull(projectId, "Project ID can not be null.");
    Assert.notNull(userId, "User ID can not be null.");
    LOG.info("Check if user {} has access to project {}.", userId, projectId);

    ProjectUser projectUser = projectUserRepository
        .findByProjectIdAndUserIdAndActive(projectId, userId, true);
    return (projectUser != null);
  }
}
