package xyz.jeevan.api.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xyz.jeevan.api.domain.Assumption;

@Repository
public interface AssumptionRepository extends MongoRepository<Assumption, String> {

  Page<Assumption> findByAndOrgIdAndActive(String orgId, boolean active, Pageable pageable);

  Assumption findByReferenceNameAndOrgId(String referenceName, String orgId);

  List<Assumption> findByOrgIdAndActive(String orgId, boolean active);
}
