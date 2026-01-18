package team.weero.app.adapter.out.student.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.student.entity.StudentJpaEntity;

@Repository
public interface StudentRepository extends CrudRepository<StudentJpaEntity, UUID> {

  Optional<StudentJpaEntity> findByUser_Id(UUID userId);
}
