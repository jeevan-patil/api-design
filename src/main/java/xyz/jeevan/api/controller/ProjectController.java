package xyz.jeevan.api.controller;

import static xyz.jeevan.api.utils.AppConstants.FIELDS;
import static xyz.jeevan.api.utils.AppConstants.LIMIT;
import static xyz.jeevan.api.utils.AppConstants.PAGE;
import static xyz.jeevan.api.utils.AppConstants.SORT_BY;
import static xyz.jeevan.api.utils.AppConstants.SORT_DIR;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.jeevan.api.domain.Project;
import xyz.jeevan.api.domain.message.ResponseMessage;
import xyz.jeevan.api.key.ProjectKey;
import xyz.jeevan.api.service.project.ProjectService;
import xyz.jeevan.api.utils.APIEndpoints;

/**
 * Created by jeevan on 16/7/17.
 */
@RestController
@Api(value = "projects", description = "Project APIs")
@RequestMapping(value = APIEndpoints.PROJECT_API_URL)
public class ProjectController extends BaseController {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(ProjectController.class);

  @Autowired
  private ProjectService projectService;

  /**
   * API to fetch project by id.
   */
  @ApiOperation(value = "Fetch project by id.",
      notes = "API to retrieve a single organization.", response = ResponseMessage.class)
  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Project> getById(@PathVariable final String id, ProjectKey projectKey) {
    LOG.info("Fetch project by id {}", id);
    return new ResponseEntity<>(limitDataFields(projectService.getById(id), Project.class),
        HttpStatus.CREATED);
  }

  /**
   * API to create new project.
   */
  @ApiOperation(value = "Create a project.", notes = "API to create new project.",
      response = Project.class)
  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Project> create(@RequestBody Project project) {
    LOG.info("Saving new project with name {}.", project.getName());
    projectService.create(project);
    return new ResponseEntity<>(limitDataFields(project, Project.class), HttpStatus.CREATED);
  }

  @ApiOperation(value = "Fetch list of projects from an organization.", notes = "Fetch list of projects from an organization.",
      response = Project.class)
  @RequestMapping(value = "/org/{orgId}/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Project>> search(
      @PathVariable String orgId,
      @RequestParam(value = PAGE, required = false) Integer page,
      @RequestParam(value = LIMIT, required = false) Integer limit,
      @RequestParam(value = SORT_DIR, required = false) String sortDir,
      @RequestParam(value = SORT_BY, required = false) String sortBy,
      @RequestParam(value = FIELDS, required = false) String fields) {
    LOG.info("Fetch list of projects.");
    List<Project> projects = projectService.search(orgId, page, limit, sortBy, sortDir);
    return new ResponseEntity<>(
        limitDataFields(projects, Project.class, fields),
        HttpStatus.OK);
  }
}
