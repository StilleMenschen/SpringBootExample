package tech.tystnad.joblog.auth;

import tech.tystnad.joblog.user.User;

public interface AuthService {
    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
