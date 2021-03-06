package xyz.jeevan.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xyz.jeevan.api.domain.Organization;

/**
 * Created by jeevan on 10/6/17.
 */

@Repository
public interface OrganizationRepository extends MongoRepository<Organization, String> {

  /**
   * Find organization by name.
   *
   * @param name Organization name.
   * @return {@link Organization} Organization Object.
   */
  Organization findByName(String name);
}
