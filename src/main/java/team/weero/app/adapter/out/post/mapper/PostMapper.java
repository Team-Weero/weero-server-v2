package team.weero.app.adapter.out.post.mapper;

import team.weero.app.adapter.out.post.entity.PostJpaEntity;
import team.weero.app.adapter.out.student.entity.StudentJpaEntity;
import team.weero.app.domain.post.model.Post;

public class PostMapper {

    public static Post toDomain(PostJpaEntity entity) {
        return new Post(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getStudent().getId(),
                entity.getDeletedTime() != null
        );
    }

    public static PostJpaEntity toEntity(Post post, StudentJpaEntity student) {
        return PostJpaEntity.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .student(student)
                .build();
    }
}
