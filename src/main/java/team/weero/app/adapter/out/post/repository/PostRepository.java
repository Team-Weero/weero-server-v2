package team.weero.app.adapter.out.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.post.entity.PostJpaEntity;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<PostJpaEntity, UUID> {
}
