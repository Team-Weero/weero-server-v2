package team.weero.app.adapter.out.counsel.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.counsel.entity.CounselRequestJpaEntity;

@Repository
public interface CounselRequestRepository extends JpaRepository<CounselRequestJpaEntity, UUID> {}
