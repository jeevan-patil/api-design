package xyz.jeevan.api.service;

import xyz.jeevan.api.domain.Organization;

/**
 * Created by jeevan on 10/6/17.
 */

public interface OrganizationService {

  /**
   * Method to save an organization data.
   */
  Organization save(Organization org);

  /**
   * This method fetches the organization data by id.
   */
  Organization getById(String id);
}
