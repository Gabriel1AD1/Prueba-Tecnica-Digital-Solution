package org.example.infrastructure.adapters.out.repository;

import org.example.domain.models.User;
import org.example.domain.ports.output.UserRepository;
import org.example.infrastructure.adapters.exception.SocialNetworkException;
import org.example.infrastructure.adapters.exception.UserNotFound;
import org.example.infrastructure.adapters.out.annotation.Repository;
import org.example.infrastructure.adapters.out.entity.UserEntity;
import org.example.infrastructure.adapters.out.mapper.UserEntityMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private final Map<String, UserEntity> users = new HashMap<>();

    @Override
    public User findByUsername(String username) {
        Optional<UserEntity> userEntity = Optional.ofNullable(users.get(username));
        var userEntityRecuperated = userEntity.orElseThrow(
                ()-> new  UserNotFound("No encontrado usuario " + username)
        );
        return UserEntityMapper.toEntity(userEntityRecuperated);
    }

    @Override
    public void save(User user) {
        UserEntity userEntity = UserEntityMapper.toDomain(user);
        users.put(user.getUsername(), userEntity);
    }
}
