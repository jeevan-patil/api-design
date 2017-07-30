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
import xyz.jeevan.api.domain.Assumption;
import xyz.jeevan.api.domain.ProjectAssumption;
import xyz.jeevan.api.service.assumption.AssumptionService;
import xyz.jeevan.api.service.assumption.ProjectAssumptionService;
import xyz.jeevan.api.utils.APIEndpoints;

@RestController
@Api(value = "assumptions", description = "Assumption APIs")
@RequestMapping(value = APIEndpoints.ASSUMPTION_API_URL)
public class AssumptionController extends BaseController {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(AssumptionController.class);

  @Autowired
  private AssumptionService assumptionService;

  @Autowired
  private ProjectAssumptionService projectAssumptionService;

  @ApiOperation(value = "Fetch list of assumptions of an organization.",
      notes = "API to fetch assumptions of an organization.", response = Assumption.class)
  @RequestMapping(value = "/org/{orgId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Assumption>> fetchOrganizationAssumptions(@PathVariable String orgId,
      @RequestParam(value = PAGE, required = false) Integer page,
      @RequestParam(value = LIMIT, required = false) Integer limit,
      @RequestParam(value = FIELDS, required = false) String fields) {
    LOG.info("Fetch list of assumptions for an organization {}.", orgId);
    List<Assumption> assumptions = assumptionService
        .findOrganizationAssumptions(orgId, page, limit);
    return new ResponseEntity<>(limitDataFields(assumptions, Assumption.class, fields),
        HttpStatus.OK);
  }

  @ApiOperation(value = "Fetch list of project assumptions.",
      notes = "API to fetch assumptions of a project.", response = ProjectAssumption.class)
  @RequestMapping(value = "/project/{projectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ProjectAssumption> > findProjectAssumptions(
      @PathVariable String projectId,
      @RequestParam(value = SORT_DIR, required = false) String sortDir,
      @RequestParam(value = SORT_BY, required = false) String sortBy,
      @RequestParam(value = FIELDS, required = false) String fields) {
    LOG.info("Fetch list of assumptions for project {}.", projectId);
    List<ProjectAssumption> projectAssumptions = projectAssumptionService
        .findByProject(projectId, sortBy, sortDir);
    return new ResponseEntity<>(
        limitDataFields(projectAssumptions, ProjectAssumption.class, fields),
        HttpStatus.OK);
  }

  @ApiOperation(value = "Create an assumption.", notes = "API to create new assumption.",
      response = Assumption.class)
  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Assumption> create(@RequestBody Assumption assumption) {
    LOG.info("Saving new assumption with label {}.", assumption.getLabel());
    assumption = assumptionService.create(assumption);
    return new ResponseEntity<>(limitDataFields(assumption, Assumption.class), HttpStatus.CREATED);
  }

}
