package team.weero.app.adapter.out.answer;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.answer.entity.AnswerJpaEntity;
import team.weero.app.adapter.out.answer.mapper.AnswerMapper;
import team.weero.app.adapter.out.answer.repository.AnswerRepository;
import team.weero.app.adapter.out.post.entity.PostJpaEntity;
import team.weero.app.adapter.out.post.repository.PostRepository;
import team.weero.app.adapter.out.user.entity.UserJpaEntity;
import team.weero.app.adapter.out.user.repository.UserRepository;
import team.weero.app.application.exception.post.PostNotFoundException;
import team.weero.app.application.exception.user.UserNotFoundException;
import team.weero.app.application.port.out.answer.CreateAnswerPort;
import team.weero.app.application.port.out.answer.DeleteAnswerPort;
import team.weero.app.application.port.out.answer.GetAnswerPort;
import team.weero.app.domain.answer.model.Answer;

@Component
@RequiredArgsConstructor
public class AnswerPersistenceAdapter implements CreateAnswerPort, GetAnswerPort, DeleteAnswerPort {

  private final AnswerRepository answerRepository;
  private final UserRepository userRepository;
  private final PostRepository postRepository;
  private final AnswerMapper answerMapper;

  @Override
  public void save(String answer, UUID userId, UUID postId) {

    UserJpaEntity user =
        userRepository.findById(userId).orElseThrow(() -> UserNotFoundException.INSTANCE);

    PostJpaEntity post =
        postRepository.findById(postId).orElseThrow(() -> PostNotFoundException.INSTANCE);

    AnswerJpaEntity entity = AnswerJpaEntity.builder().answer(answer).user(user).post(post).build();

    answerRepository.save(entity);
  }

  @Override
  public void softDelete(UUID answerId) {
    AnswerJpaEntity answer =
        answerRepository
                .findByIdAndDeletedAtIsNull(answerId)
                .orElseThrow(() -> PostNotFoundException.INSTANCE);

    answer.markDeleted();
    answerRepository.save(answer);
  }

  @Override
  public List<Answer> getAll(UUID postId) {
    PostJpaEntity post =
            postRepository.findById(postId).orElseThrow(() -> PostNotFoundException.INSTANCE);

    List<AnswerJpaEntity> answers = answerRepository.findByPost(post);

    return answers.stream()
            .map(answerMapper::toDomain)
            .toList();
  }
}
