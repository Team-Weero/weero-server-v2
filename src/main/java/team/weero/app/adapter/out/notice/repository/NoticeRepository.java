package team.weero.app.adapter.out.notice.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.notice.entity.NoticeJpaEntity;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeJpaEntity, UUID> {

  Page<NoticeJpaEntity> findAllByDeletedTimeIsNull(Pageable pageable);

  Optional<NoticeJpaEntity> findByIdAndDeletedTimeIsNull(UUID id);
}
