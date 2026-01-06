package team.weero.app.adapter.out.persistence.student.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.persistence.student.entity.StudentJpaEntity;

@Repository
public interface StudentJpaRepository extends JpaRepository<StudentJpaEntity, UUID> {
  Optional<StudentJpaEntity> findByAccountId(String accountId);
}
