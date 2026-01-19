package team.weero.app.adapter.out.post.mapper;

import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.post.entity.PostJpaEntity;
import team.weero.app.adapter.out.student.entity.StudentJpaEntity;
import team.weero.app.domain.post.model.Post;

@Component
public class PostMapper {

  public Post toDomain(PostJpaEntity entity) {
    return Post.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .content(entity.getContent())
        .studentId(entity.getStudent().getId())
        .studentName(entity.getStudent().getName())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .deletedAt(entity.getDeletedAt())
        .build();
  }

  public static PostJpaEntity toEntity(Post post, StudentJpaEntity student) {
    return PostJpaEntity.builder()
        .title(post.getTitle())
        .content(post.getContent())
        .student(student)
        .build();
  }
}
