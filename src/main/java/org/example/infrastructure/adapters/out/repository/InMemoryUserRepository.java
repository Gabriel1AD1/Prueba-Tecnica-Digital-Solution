package org.example.infrastructure.adapters.out.repository;

import org.example.domain.models.User;
import org.example.application.ports.output.UserRepository;
import org.example.infrastructure.adapters.exception.UserNotFound;
import org.example.infrastructure.adapters.out.entity.UserEntity;
import org.example.infrastructure.adapters.out.mapper.UserEntityMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {
    private static InMemoryUserRepository instance;
    private final Map<String, UserEntity> users = new HashMap<>();

    private InMemoryUserRepository() {}

    public static InMemoryUserRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryUserRepository();
        }
        return instance;
    }

    @Override
    public User findByUsername(String username) {
        Optional<UserEntity> userEntity = Optional.ofNullable(users.get(username));
        UserEntity userEntityRecuperated = userEntity.orElseThrow(
                () -> new UserNotFound("User not found: " + username)
        );
        return UserEntityMapper.toEntity(userEntityRecuperated);
    }

    @Override
    public void save(User user) {
        UserEntity userEntity = UserEntityMapper.toDomain(user);
        users.put(user.getUsername(), userEntity);
    }
}

