package xyz.jeevan.api.service.assumption;

import java.util.List;
import xyz.jeevan.api.domain.Assumption;

public interface AssumptionService {

  /**
   * Fetch organization assumptions.
   *
   * @param orgId Organization ID.
   * @param page ResultSet page number.
   * @param limit ResultSet limit / number of records to fetch.
   * @return {@code List<Assumption>} List of assumptions.
   */
  List<Assumption> findOrganizationAssumptions(String orgId, Integer page, Integer limit);

  /**
   * Create an assumption.
   *
   * @param assumption {@link Assumption} assumption data to save.
   * @return {@code Assumption} Saved assumption data.
   */
  Assumption create(Assumption assumption);
}
