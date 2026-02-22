package team.weero.app.adapter.out.post.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.post.entity.PostJpaEntity;

@Repository
public interface PostRepository extends JpaRepository<PostJpaEntity, UUID> {

  Optional<PostJpaEntity> findByIdAndDeletedAtIsNull(UUID id);

  @Query("SELECT p FROM PostJpaEntity p WHERE p.deletedAt IS NULL ORDER BY p.createdAt DESC")
  Page<PostJpaEntity> findAllByDeletedAtIsNull(Pageable pageable);

  Page<PostJpaEntity> findAllByStudentIdAndDeletedAtIsNull(UUID studentId, Pageable pageable);

  @Modifying
  @Query("UPDATE PostJpaEntity p SET p.viewCount = p.viewCount + 1 WHERE p.id = :postId")
  void incrementViewCount(@Param("postId") UUID postId);
}
