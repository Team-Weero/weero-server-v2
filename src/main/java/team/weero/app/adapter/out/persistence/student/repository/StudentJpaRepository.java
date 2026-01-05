package team.weero.app.infrastructure.persistence.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.infrastructure.persistence.student.entity.StudentJpaEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentJpaRepository extends JpaRepository<StudentJpaEntity, UUID> {
    Optional<StudentJpaEntity> findByAccountId(String accountId);
}
