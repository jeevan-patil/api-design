package xyz.jeevan.api.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.jeevan.api.key.ProjectKey;
import xyz.jeevan.api.service.project.ProjectService;

/**
 * @author jeevan
 */
@Component
public class ProjectAccessResolver implements AccessResolver<ProjectKey> {

  @Autowired
  private ProjectService projectService;

  @Override
  public boolean check(ProjectKey authKey) {
    return projectService.checkProjectUserAccess(authKey.getId(), authKey.getUserId());
  }
}
