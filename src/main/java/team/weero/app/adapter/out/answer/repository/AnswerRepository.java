package team.weero.app.adapter.out.answer.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.answer.entity.AnswerJpaEntity;
import team.weero.app.adapter.out.post.entity.PostJpaEntity;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerJpaEntity, UUID> {

  Optional<AnswerJpaEntity> findByPost(PostJpaEntity post);
}
