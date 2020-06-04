package tech.tystnad.joblog.user;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

public class User {
    @Id
    private String id;

    @Indexed(unique=true, direction= IndexDirection.DESCENDING)
    private String username;
    private String password;
    @Indexed(unique=true, direction= IndexDirection.DESCENDING)
    private String email;
    private Date lastPasswordResetDate;
    private List<String> roles;
    public String getId() {
      return id;
    }
    public void setId(String id) {
      this.id = id;
    }
    public String getUsername() {
      return username;
    }
    public void setUsername(String username) {
      this.username = username;
    }
    public String getPassword() {
      return password;
    }
    public void setPassword(String password) {
      this.password = password;
    }
    public String getEmail() {
      return email;
    }
    public void setEmail(String email) {
      this.email = email;
    }
    public Date getLastPasswordResetDate() {
      return lastPasswordResetDate;
    }
    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
      this.lastPasswordResetDate = lastPasswordResetDate;
    }
    public List<String> getRoles() {
      return roles;
    }
    public void setRoles(List<String> roles) {
      this.roles = roles;
    }
}
