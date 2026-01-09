package team.weero.app.adapter.out.student.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.student.entity.StudentJpaEntity;

import java.util.UUID;

@Repository
public interface StudentRepository extends CrudRepository<StudentJpaEntity, UUID> {
}
