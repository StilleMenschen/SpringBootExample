package tech.tystnad.works.model;

import org.springframework.data.annotation.Id;

public class TaskGroup {
    @Id
    private String id;
    private String name;
    private int order;
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
    public int getOrder() {
      return order;
    }
    public void setOrder(int order) {
      this.order = order;
    }
    
    
}
