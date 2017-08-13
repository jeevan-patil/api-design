package xyz.jeevan.api.key;

public class ProjectKey implements ResourceKey {

  private String id;
  private String userId;

  public ProjectKey(String id, String userId) {
    this.id = id;
    this.userId = userId;
  }

  public String getId() {
    return id;
  }

  public String getUserId() {
    return userId;
  }
}
