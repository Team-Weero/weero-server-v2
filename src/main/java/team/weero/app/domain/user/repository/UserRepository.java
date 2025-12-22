package team.weero.app.domain.user.repository;

import team.weero.app.domain.user.model.User;
import team.weero.app.domain.user.model.UserRole;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * User Repository Interface (Domain Layer)
 * Defines the contract for user persistence operations
 */
public interface UserRepository {

    /**
     * Save a user
     * @param user User to save
     * @return Saved user
     */
    User save(User user);

    /**
     * Find a user by ID
     * @param id User ID
     * @return Optional of User
     */
    Optional<User> findById(UUID id);

    /**
     * Find all users by role
     * @param role User role
     * @return List of users
     */
    List<User> findByUserRole(UserRole role);

    /**
     * Find all users
     * @return List of all users
     */
    List<User> findAll();

    /**
     * Delete a user by ID
     * @param id User ID
     */
    void deleteById(UUID id);

    /**
     * Check if a user exists by ID
     * @param id User ID
     * @return true if exists, false otherwise
     */
    boolean existsById(UUID id);
}
