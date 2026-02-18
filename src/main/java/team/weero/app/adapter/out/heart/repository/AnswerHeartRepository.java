package team.weero.app.adapter.out.heart.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.heart.entity.AnswerHeartJpaEntity;

@Repository
public interface AnswerHeartRepository extends JpaRepository<AnswerHeartJpaEntity, UUID> {
  boolean existsByAnswerIdAndUserId(UUID answerId, UUID userId);

  void deleteByAnswerIdAndUserId(UUID answerId, UUID userId);

  int countByAnswerId(UUID answerId);
}
