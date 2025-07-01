package team.weero.app.persistence.counseling.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.persistence.counseling.entity.CounselingApplication;

import java.util.UUID;

@Repository
public interface CounselingRepository extends CrudRepository<CounselingApplication, UUID> {
}
