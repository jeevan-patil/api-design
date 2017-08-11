package xyz.jeevan.api.service.assumption;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import xyz.jeevan.api.domain.Assumption;
import xyz.jeevan.api.domain.Organization;
import xyz.jeevan.api.repository.AssumptionRepository;
import xyz.jeevan.api.repository.OrganizationRepository;

/**
 * @author jeevan
 */
@RunWith(MockitoJUnitRunner.class)
public class AssumptionServiceImplTest {

  private String ORG_ID = UUID.randomUUID().toString();

  @InjectMocks
  @Spy
  private AssumptionServiceImpl cut;

  @Mock
  private AssumptionRepository mockAssumptionRepository;

  @Mock
  private OrganizationRepository mockOrganizationRepository;

  @Test
  public void create() throws Exception {
    Assumption assumption = dummyAssumption("production", "text", "production_mw");
    Organization organization = dummyOrganization();

    when(mockOrganizationRepository.findOne(assumption.getOrgId()))
        .thenReturn(organization);
    when(mockAssumptionRepository
        .findByReferenceNameAndOrgId(assumption.getReferenceName(), assumption.getOrgId()))
        .thenReturn(null);

    Assumption createdAssumption = dummyAssumption("production", "text", "production_mw");

    createdAssumption.setId(UUID.randomUUID().toString());
    when(mockAssumptionRepository.save(assumption)).thenReturn(createdAssumption);

    createdAssumption = cut.create(assumption);
    assertNull(createdAssumption);
    assertNotNull(createdAssumption);
    verify(mockOrganizationRepository).findOne(assumption.getOrgId());
    verify(mockAssumptionRepository)
        .findByReferenceNameAndOrgId(assumption.getReferenceName(), assumption.getOrgId());
    verify(mockAssumptionRepository).save(assumption);
    verifyNoMoreInteractions(mockAssumptionRepository, mockOrganizationRepository);
  }

  private Assumption dummyAssumption(String label, String fieldType, String referenceName) {
    Assumption assumption = new Assumption();
    assumption.setLabel(label);
    assumption.setOrgId(ORG_ID);
    assumption.setFieldType(fieldType);
    assumption.setReferenceName(referenceName);
    assumption.setActive(true);
    return assumption;
  }

  private Organization dummyOrganization() {
    Organization organization = new Organization();
    organization.setId(ORG_ID);
    organization.setName("Power of APIs");
    organization.setActive(true);
    return organization;
  }
}
