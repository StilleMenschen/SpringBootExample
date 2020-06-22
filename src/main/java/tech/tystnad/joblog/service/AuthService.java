package tech.tystnad.joblog.service;

import tech.tystnad.joblog.model.User;

public interface AuthService {
    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
