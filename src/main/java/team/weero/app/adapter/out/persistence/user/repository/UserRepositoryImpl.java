package team.weero.app.adapter.out.persistence.user.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.weero.app.domain.user.model.User;
import team.weero.app.domain.user.model.UserRole;
import team.weero.app.application.port.out.user.UserRepository;
import team.weero.app.adapter.out.persistence.user.entity.UserJpaEntity;
import team.weero.app.adapter.out.persistence.user.mapper.UserMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


 * User Repository Implementation (Infrastructure Layer)
 * Implements the domain UserRepository interface using JPA
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        UserJpaEntity entity = userMapper.toEntity(user);
        UserJpaEntity savedEntity = userJpaRepository.save(entity);
        return userMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userJpaRepository.findById(id)
                .map(userMapper::toDomain);
    }

    @Override
    public List<User> findByUserRole(UserRole role) {
        return userJpaRepository.findByUserRole(role)
                .stream()
                .map(userMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll()
                .stream()
                .map(userMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        userJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return userJpaRepository.existsById(id);
    }
}
