package xyz.jeevan.api.controller;

import static xyz.jeevan.api.utils.AppConstants.FIELDS;
import static xyz.jeevan.api.utils.AppConstants.LIMIT;
import static xyz.jeevan.api.utils.AppConstants.PAGE;

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
import xyz.jeevan.api.domain.Organization;
import xyz.jeevan.api.domain.message.ResponseMessage;
import xyz.jeevan.api.service.OrganizationService;
import xyz.jeevan.api.utils.APIEndpoints;

/**
 * Created by jeevan on 10/6/17.
 */

@RestController
@RequestMapping(value = APIEndpoints.ORG_API_URL)
public class OrganizationController extends BaseController {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(OrganizationController.class);

  @Autowired
  private OrganizationService organizationService;

  /**
   * API to save an organization data and return required fields.
   */
  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Organization> save(@RequestBody Organization organization) {
    LOG.info("Saving new organization with name {}.", organization.getName());
    Organization savedOrg = organizationService.save(organization);
    return new ResponseEntity<>(limitDataFields(savedOrg, Organization.class), HttpStatus.CREATED);
  }

  /**
   * API to fetch organization data by id and return required fields.
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Organization> getById(@PathVariable("id") final String id,
      @RequestParam(value = FIELDS, required = false) final String fields) {
    LOG.info("Get organization details by id {}.", id);
    Organization organization = organizationService.getById(id);
    return new ResponseEntity<>(limitDataFields(organization, Organization.class, fields),
        HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseMessage> delete(@PathVariable final String id) {
    LOG.info("Delete an organization by id {}.", id);
    organizationService.delete(id);
    return new ResponseEntity<>(success("Organization has been deleted successfully."),
        HttpStatus.OK);
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Organization>> list(
      @RequestParam(value = PAGE, required = false) Integer page,
      @RequestParam(value = LIMIT, required = false) Integer limit,
      @RequestParam(value = FIELDS, required = false) String fields) {
    LOG.info("Fetch list of organizations.");
    List<Organization> organizations = organizationService.list(page, limit);
    return new ResponseEntity<>(
        limitDataFields(organizations, Organization.class, fields),
        HttpStatus.OK);
  }

}
