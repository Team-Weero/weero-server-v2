package team.weero.app.adapter.out.persistence.notice.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import team.weero.app.adapter.out.persistence.notice.entity.NoticeJpaEntity;
import team.weero.app.adapter.out.persistence.user.entity.UserJpaEntity;

public interface NoticeJpaRepository extends JpaRepository<NoticeJpaEntity, UUID> {
  Page<NoticeJpaEntity> findAllByOrderByIdDesc(Pageable pageable);

  List<NoticeJpaEntity> findByUserOrderByIdDesc(UserJpaEntity user);
}
