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
        .nickName(entity.getStudent().getNickname())
        .viewCount(entity.getViewCount())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .deletedAt(entity.getDeletedAt())
        .build();
  }

  public static PostJpaEntity toEntity(Post post, StudentJpaEntity student) {
    return PostJpaEntity.builder()
        .id(post.getId())
        .title(post.getTitle())
        .content(post.getContent())
        .student(student)
        .viewCount(post.getViewCount())
        .createdAt(post.getCreatedAt())
        .updatedAt(post.getUpdatedAt())
        .deletedAt(post.getDeletedAt())
        .build();
  }
}
