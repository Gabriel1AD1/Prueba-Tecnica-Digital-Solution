package org.example.application.ports.output;

import org.example.domain.models.User;

public interface UserRepository {
    User findByUsername(String username);
    void save(User user);
}
