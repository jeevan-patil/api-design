package xyz.jeevan.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xyz.jeevan.api.domain.Assumption;

@Repository
public interface AssumptionRepository extends MongoRepository<Assumption, String> {

  /**
   * Method to fetch organization assumptions. Pagination is supported.
   *
   * @param orgId Organization ID.
   * @param active Indicator to fetch active assumptions.
   * @param pageable {@link PageRequest} object containing information about paging and sorting.
   * @return {@code Page<Assumption>} Paged assumptions list.
   */
  Page<Assumption> findByOrgIdAndActive(String orgId, boolean active, Pageable pageable);

  /**
   * Fetch assumption by organization and reference name.
   *
   * @param referenceName Unique reference name.
   * @param orgId Organization ID.
   * @return {@link Assumption} assumption object.
   */
  Assumption findByReferenceNameAndOrgId(String referenceName, String orgId);
}
