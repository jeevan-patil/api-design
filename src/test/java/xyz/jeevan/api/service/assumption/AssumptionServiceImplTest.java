package xyz.jeevan.api.service.assumption;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.util.ReflectionTestUtils;
import xyz.jeevan.api.domain.Assumption;
import xyz.jeevan.api.domain.Organization;
import xyz.jeevan.api.helper.PaginationHelper;
import xyz.jeevan.api.repository.AssumptionRepository;
import xyz.jeevan.api.repository.OrganizationRepository;

/**
 * @author jeevan
 */
@RunWith(MockitoJUnitRunner.class)
public class AssumptionServiceImplTest {

  private String ORG_ID = randomString();

  @InjectMocks
  @Spy
  private AssumptionServiceImpl cut;

  @Mock
  private AssumptionRepository mockAssumptionRepository;

  @Mock
  private OrganizationRepository mockOrganizationRepository;

  @Mock
  private PaginationHelper mockPaginationHelper;

  @Before
  public void setUp() {
    ReflectionTestUtils.setField(cut, "defaultPageSize", 100);
    ReflectionTestUtils.setField(cut, "maxPageSize", 1000);
  }

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

    createdAssumption.setId(randomString());
    when(mockAssumptionRepository.save(assumption)).thenReturn(createdAssumption);

    createdAssumption = cut.create(assumption);
    assertNotNull(createdAssumption);
    verify(mockOrganizationRepository).findOne(assumption.getOrgId());
    verify(mockAssumptionRepository)
        .findByReferenceNameAndOrgId(assumption.getReferenceName(), assumption.getOrgId());
    verify(mockAssumptionRepository).save(assumption);
    verifyNoMoreInteractions(mockAssumptionRepository, mockOrganizationRepository);
  }

  @Test
  public void findOrganizationAssumptions() throws Exception {
    int page = 1, limit = 10;
    when(mockPaginationHelper.refinePageNumber(page)).thenReturn(page);
    when(mockPaginationHelper.validateResponseLimit(limit, 100, 1000)).thenReturn(limit);

    List<Assumption> assumptions = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      assumptions.add(dummyAssumption(randomString(), randomString(),
          randomString()));
    }

    Page<Assumption> pagedAssumptions = new PageImpl<>(assumptions);

    when(mockAssumptionRepository
        .findByOrgIdAndActive(ORG_ID, true, new PageRequest(page, limit)))
        .thenReturn(pagedAssumptions);

    List<Assumption> results = cut.findOrganizationAssumptions(ORG_ID, page, limit);
    assertNotNull(results);
    assertEquals(assumptions.size(), results.size());
    verify(mockAssumptionRepository)
        .findByOrgIdAndActive(ORG_ID, true, new PageRequest(page, limit));
    verify(mockPaginationHelper).refinePageNumber(page);
    verify(mockPaginationHelper).validateResponseLimit(limit, 100, 1000);
    verifyNoMoreInteractions(mockPaginationHelper, mockAssumptionRepository);
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

  private String randomString() {
    return UUID.randomUUID().toString();
  }
}
