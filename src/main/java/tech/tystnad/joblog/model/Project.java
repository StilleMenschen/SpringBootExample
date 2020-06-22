package tech.tystnad.joblog.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Project {
  @Id
  private String id;
  private String name;
  private String desc;
  private boolean enabled;
  private boolean archived;
  @DBRef(lazy = true)
  private User owner;
  @DBRef(lazy = true)
  private List<User> members;
  @DBRef(lazy = true)
  private List<TaskGroup> groups;

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

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public boolean isArchived() {
    return archived;
  }

  public void setArchived(boolean archived) {
    this.archived = archived;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public List<User> getMembers() {
    return members;
  }

  public void setMembers(List<User> members) {
    this.members = members;
  }

  public List<TaskGroup> getGroups() {
    return groups;
  }

  public void setGroups(List<TaskGroup> groups) {
    this.groups = groups;
  }
}
