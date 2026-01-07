package team.weero.app.adapter.out.teacher.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.teacher.entity.TeacherJpaEntity;

import java.util.UUID;

@Repository
public interface TeacherRepository extends CrudRepository<TeacherJpaEntity, UUID> {
}
