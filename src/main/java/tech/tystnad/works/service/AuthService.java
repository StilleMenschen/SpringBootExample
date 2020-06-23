package tech.tystnad.works.service;

import tech.tystnad.works.model.User;

public interface AuthService {
    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
