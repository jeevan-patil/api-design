package xyz.jeevan.api.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import xyz.jeevan.api.domain.Assumption;
import xyz.jeevan.api.domain.Project;
import xyz.jeevan.api.event.EntityCreatedEvent;
import xyz.jeevan.api.service.assumption.ProjectAssumptionService;

/**
 * Send notifications or perform some other actions whenever any new entity is created.
 */
@Component
public class EntityCreatedEventListener implements ApplicationListener<EntityCreatedEvent> {

  @Autowired
  private ProjectAssumptionService projectAssumptionService;

  @Override
  public void onApplicationEvent(EntityCreatedEvent event) {
    if (event.getEntity() != null) {
      if (event.getEntity() instanceof Assumption) {
        projectAssumptionService.copyNewAssumptionInProjects((Assumption) event.getEntity());
      } else if (event.getEntity() instanceof Project) {
        projectAssumptionService.createProjectAssumptions((Project) event.getEntity());
      }
    }
  }
}
