package xyz.jeevan.api.service.assumption;

import java.util.List;
import xyz.jeevan.api.domain.Assumption;

public interface AssumptionService {

  List<Assumption> getOrganizationAssumptions(String orgId, Integer page, Integer limit);

  Assumption create(Assumption assumption);

  void copyAssumptionInProjects(Assumption assumption);

  void migrateAssumptions();
}
