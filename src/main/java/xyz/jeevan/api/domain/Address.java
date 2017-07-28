package xyz.jeevan.api.domain;

import xyz.jeevan.api.annotation.DefaultField;

/**
 * Created by jeevan on 19/6/17.
 */
public class Address {

  @DefaultField
  private String city;
  private String state;

  @DefaultField
  private String zip;
  private String country;

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }
}
