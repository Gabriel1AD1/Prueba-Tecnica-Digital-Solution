package org.example.infrastructure.adapters.out.repository;

import org.example.domain.models.User;
import org.example.domain.ports.output.UserRepository;
import org.example.infrastructure.adapters.out.annotation.Repository;
import org.example.infrastructure.adapters.out.entity.UserEntity;
import org.example.infrastructure.adapters.out.mapper.UserEntityMapper;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private final Map<String, UserEntity> users = new HashMap<>();

    @Override
    public User findByUsername(String username) {
        UserEntity userEntity = users.get(username);
        return userEntity != null ? UserEntityMapper.toEntity(userEntity) : null;
    }

    @Override
    public void save(User user) {
        UserEntity userEntity = UserEntityMapper.toDomain(user);
        users.put(user.getUsername(), userEntity);
    }
}
