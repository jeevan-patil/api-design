package xyz.jeevan.api.service.organization;

import java.util.List;
import xyz.jeevan.api.domain.Organization;

/**
 * Created by jeevan on 10/6/17.
 */

public interface OrganizationService {

  /**
   * Method to save an organization data.
   *
   * @param org Organization object to save.
   * @return {@code Organization} Organization object.
   */
  Organization save(Organization org);

  /**
   * This method fetches the organization data by id.
   *
   * @param id organization id
   * @return {@code Organization} organization object.
   */
  Organization getById(String id);

  /**
   * Method to delete an organization by id.
   *
   * @param id organization id
   */
  void delete(String id);

  /**
   * Method to fetch list of organizations.
   *
   * @param page response page number.
   * @param limit number of records to fetch.
   * @return {@code List<Organization>}
   */
  List<Organization> list(Integer page, Integer limit);
}
