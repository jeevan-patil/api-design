package xyz.jeevan.api.domain;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by jeevan on 10/6/17.
 */

@Document(collection = "organizations")
public class Organization {

  @Id
  private String id;

  @Indexed
  private String name;
  private String logoUrl;
  private boolean active;
  private String contact;
  private String address;
  private int employeeCount;
  private String website;
  private String email;
  private String facebookPage;
  private String twitterHandle;
  private Date createdAt;
  private Date updatedAt;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLogoUrl() {
    return logoUrl;
  }

  public void setLogoUrl(String logoUrl) {
    this.logoUrl = logoUrl;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getEmployeeCount() {
    return employeeCount;
  }

  public void setEmployeeCount(int employeeCount) {
    this.employeeCount = employeeCount;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFacebookPage() {
    return facebookPage;
  }

  public void setFacebookPage(String facebookPage) {
    this.facebookPage = facebookPage;
  }

  public String getTwitterHandle() {
    return twitterHandle;
  }

  public void setTwitterHandle(String twitterHandle) {
    this.twitterHandle = twitterHandle;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }
}
