package tech.tystnad.joblog.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Todo是一个领域对象（domain object）
 */
public class Todo {
    @Id private String id;
    private String desc;
    private boolean completed;
    @DBRef(lazy = true)
    private TaskGroup group;
    @DBRef(lazy = true)
    private User owner;
    @DBRef(lazy = true)
    private List<User> participants;
    private Date dueDate;
    private Date reminder;
    private int priority;
    private String remark;
    public String getId() {
      return id;
    }
    public void setId(String id) {
      this.id = id;
    }
    public String getDesc() {
      return desc;
    }
    public void setDesc(String desc) {
      this.desc = desc;
    }
    public boolean isCompleted() {
      return completed;
    }
    public void setCompleted(boolean completed) {
      this.completed = completed;
    }
    public TaskGroup getGroup() {
      return group;
    }
    public void setGroup(TaskGroup group) {
      this.group = group;
    }
    public User getOwner() {
      return owner;
    }
    public void setOwner(User owner) {
      this.owner = owner;
    }
    public List<User> getParticipants() {
      return participants;
    }
    public void setParticipants(List<User> participants) {
      this.participants = participants;
    }
    public Date getDueDate() {
      return dueDate;
    }
    public void setDueDate(Date dueDate) {
      this.dueDate = dueDate;
    }
    public Date getReminder() {
      return reminder;
    }
    public void setReminder(Date reminder) {
      this.reminder = reminder;
    }
    public int getPriority() {
      return priority;
    }
    public void setPriority(int priority) {
      this.priority = priority;
    }
    public String getRemark() {
      return remark;
    }
    public void setRemark(String remark) {
      this.remark = remark;
    }
}
