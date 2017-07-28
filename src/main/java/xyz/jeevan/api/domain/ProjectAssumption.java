package xyz.jeevan.api.domain;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import xyz.jeevan.api.annotation.DefaultField;

@Document(collection = "project_assumptions")
public class ProjectAssumption {

  @Id
  @DefaultField
  private String id;

  @Indexed
  private String projectId;

  @Indexed
  @DefaultField
  private String assumptionId;

  @DefaultField
  private String value;
  private String unit;
  private Date createdAt;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public String getAssumptionId() {
    return assumptionId;
  }

  public void setAssumptionId(String assumptionId) {
    this.assumptionId = assumptionId;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
