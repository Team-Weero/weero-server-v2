package team.weero.app.adapter.out.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.notice.entity.NoticeJpaEntity;

import java.util.UUID;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeJpaEntity, UUID> {
}
