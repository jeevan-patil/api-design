package xyz.jeevan.api.service.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.jeevan.api.domain.Project;
import xyz.jeevan.api.domain.User;
import xyz.jeevan.api.repository.OrganizationRepository;
import xyz.jeevan.api.repository.ProjectRepository;
import xyz.jeevan.api.utils.DateUtil;

@Service
public class ProjectServiceImpl implements ProjectService {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(ProjectServiceImpl.class);

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private OrganizationRepository organizationRepository;

  @Override
  public void create(Project project) {
    User user = new User();
    user.setActive(true);
    user.setCreatedAt(DateUtil.now());
    user.setEmail("jeevanpaatil@gmail.com");
    user.setLocked(false);
    user.setOrganizationId(organizationRepository.findAll().get(0).getId());

    //throw new UnsupportedOperationException();
  }
}
