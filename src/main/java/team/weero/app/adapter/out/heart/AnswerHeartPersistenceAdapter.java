package team.weero.app.adapter.out.heart;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.out.heart.entity.AnswerHeartJpaEntity;
import team.weero.app.adapter.out.heart.repository.AnswerHeartRepository;
import team.weero.app.application.port.out.heart.AnswerHeartPort;

@Component
@RequiredArgsConstructor
public class AnswerHeartPersistenceAdapter implements AnswerHeartPort {

  private final AnswerHeartRepository answerHeartRepository;

  @Override
  public boolean exists(UUID answerId, UUID userId) {
    return answerHeartRepository.existsByAnswerIdAndUserId(answerId, userId);
  }

  @Override
  public void save(UUID answerId, UUID userId) {
    answerHeartRepository.save(AnswerHeartJpaEntity.of(answerId, userId));
  }

  @Override
  @Transactional
  public void delete(UUID answerId, UUID userId) {
    answerHeartRepository.deleteByAnswerIdAndUserId(answerId, userId);
  }

  @Override
  public int countByAnswerId(UUID answerId) {
    return answerHeartRepository.countByAnswerId(answerId);
  }
}
