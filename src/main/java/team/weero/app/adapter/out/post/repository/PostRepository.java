package team.weero.app.adapter.out.post.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.post.entity.PostJpaEntity;

@Repository
public interface PostRepository extends JpaRepository<PostJpaEntity, UUID> {

  Optional<PostJpaEntity> findByIdAndDeletedAtIsNull(UUID id);

  List<PostJpaEntity> findAllByDeletedAtIsNullOrderByCreatedAtDesc();

  List<PostJpaEntity> findAllByStudentIdAndDeletedAtIsNullOrderByCreatedAtDesc(UUID studentId);

  @Modifying
  @Query("UPDATE PostJpaEntity p SET p.viewCount = p.viewCount + 1 WHERE p.id = :postId")
  void incrementViewCount(@Param("postId") UUID postId);
}
