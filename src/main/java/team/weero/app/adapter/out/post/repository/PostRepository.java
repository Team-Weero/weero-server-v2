package team.weero.app.adapter.out.post.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.post.entity.PostJpaEntity;

@Repository
public interface PostRepository extends JpaRepository<PostJpaEntity, UUID> {

  Optional<PostJpaEntity> findByIdAndDeletedAtIsNull(UUID id);

  List<PostJpaEntity> findAllByDeletedAtIsNullOrderByCreatedAtDesc();

  List<PostJpaEntity> findAllByStudentIdAndDeletedAtIsNullOrderByCreatedAtDesc(UUID studentId);
}
