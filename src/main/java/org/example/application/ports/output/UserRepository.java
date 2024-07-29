package org.example.application.ports.output;

import org.example.domain.models.User;

public interface UserRepository {
    User findByUsername(String username);
    User save(User user);

    boolean existByUsername(String username);
}
