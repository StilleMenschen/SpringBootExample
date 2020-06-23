package tech.tystnad.works.model;

public class Auth {
  private String token;
  private User user;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Auth(String token, User user) {
    this.token = token;
    this.user = user;
    user.setPassword(null);
    user.setLastPasswordResetDate(null);
  }
}
