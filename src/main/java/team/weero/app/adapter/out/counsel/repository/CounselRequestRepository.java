package team.weero.app.adapter.out.counsel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.counsel.entity.CounselRequestJpaEntity;

import java.util.UUID;

@Repository
public interface CounselRequestRepository extends JpaRepository<CounselRequestJpaEntity, UUID> {
}
