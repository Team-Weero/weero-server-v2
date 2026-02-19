package team.weero.app.adapter.out.answer.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.answer.entity.AnswerJpaEntity;
import team.weero.app.adapter.out.post.entity.PostJpaEntity;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerJpaEntity, UUID> {

  @Query(
      "SELECT a FROM AnswerJpaEntity a "
          + "JOIN FETCH a.user u "
          + "LEFT JOIN FETCH u.student "
          + "LEFT JOIN FETCH u.teacher "
          + "WHERE a.post = :post AND a.deletedAt IS NULL "
          + "ORDER BY a.createdAt DESC")
  List<AnswerJpaEntity> findByPost(PostJpaEntity post);

  Optional<AnswerJpaEntity> findByIdAndDeletedAtIsNull(UUID id);

  @Query(
          "SELECT a FROM AnswerJpaEntity a "
                  + "JOIN FETCH a.user u "
                  + "WHERE a.id = :id AND a.deletedAt IS NULL")
  Optional<AnswerJpaEntity> findByIdWithUser(@Param("id") UUID id);
}
