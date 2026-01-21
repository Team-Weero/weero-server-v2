package team.weero.app.adapter.out.answer.mapper;

import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.answer.entity.AnswerJpaEntity;
import team.weero.app.adapter.out.post.entity.PostJpaEntity;
import team.weero.app.adapter.out.user.entity.UserJpaEntity;
import team.weero.app.domain.answer.model.Answer;

@Component
public class AnswerMapper {

  public Answer toDomain(AnswerJpaEntity entity) {
    UserJpaEntity user = entity.getUser();

    return Answer.builder()
        .id(entity.getId())
        .answer(entity.getAnswer())
        .userId(user.getId())
        .postId(entity.getPost().getId())
        .nickName(getDisplayName(user))
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .deletedAt(entity.getDeletedAt())
        .build();
  }

  public static AnswerJpaEntity toEntity(Answer answer, UserJpaEntity user, PostJpaEntity post) {
    return AnswerJpaEntity.builder().answer(answer.getAnswer()).user(user).post(post).build();
  }

  /** User 역할에 따른 표시 이름 반환하기 위해 teacher은 이름 student는 닉네임 */
  private String getDisplayName(UserJpaEntity user) {
    if (user.getStudent() != null) {
      return user.getStudent().getNickname();
    } else if (user.getTeacher() != null) {
      return user.getTeacher().getName();
    }
    return "알 수 없음";
  }
}
