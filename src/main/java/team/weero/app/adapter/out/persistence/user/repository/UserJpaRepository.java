package team.weero.app.adapter.out.persistence.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.weero.app.domain.user.model.UserRole;
import team.weero.app.adapter.out.persistence.user.entity.UserJpaEntity;

import java.util.List;
import java.util.UUID;


 * User JPA Repository Interface (Infrastructure Layer)
 * Spring Data JPA repository for UserJpaEntity
 */
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {

    
     * Find all users by role
     * @param userRole User role
     * @return List of UserJpaEntity
     */
    List<UserJpaEntity> findByUserRole(UserRole userRole);
}
