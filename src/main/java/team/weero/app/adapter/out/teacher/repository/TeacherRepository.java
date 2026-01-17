package team.weero.app.adapter.out.teacher.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.teacher.entity.TeacherJpaEntity;

@Repository
public interface TeacherRepository extends CrudRepository<TeacherJpaEntity, UUID> {

  Optional<TeacherJpaEntity> findByUserId(UUID userId);
}
