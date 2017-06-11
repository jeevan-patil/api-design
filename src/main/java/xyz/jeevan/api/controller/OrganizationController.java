package xyz.jeevan.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.jeevan.api.domain.Organization;
import xyz.jeevan.api.service.OrganizationService;
import xyz.jeevan.api.utils.APIEndpoints;

/**
 * Created by jeevan on 10/6/17.
 */

@RestController
@RequestMapping(value = APIEndpoints.ORG_API_URL)
public class OrganizationController {
  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(OrganizationController.class);

  @Autowired
  private OrganizationService organizationService;

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Organization> save(@RequestBody Organization organization) {
    LOG.info("Saving new organization with name {}", organization.getName());
    return new ResponseEntity<>(organizationService.save(organization), HttpStatus.CREATED);
  }
}
