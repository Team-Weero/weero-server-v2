package team.weero.app.application.service.answer;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.port.in.answer.GetAnswerUseCase;
import team.weero.app.application.port.in.answer.dto.response.GetAnswerInfo;
import team.weero.app.application.port.out.answer.GetAnswerPort;
import team.weero.app.application.port.out.heart.AnswerHeartPort;

@Service
@RequiredArgsConstructor
public class GetAnswerService implements GetAnswerUseCase {

  private final GetAnswerPort getAnswerPort;
  private final AnswerHeartPort answerHeartPort;

  @Transactional(readOnly = true)
  @Override
  public List<GetAnswerInfo> execute(UUID postId, UUID userId) {
    return getAnswerPort.getAll(postId).stream()
            .map(a -> new GetAnswerInfo(
                    a.getId(),
                    a.getAnswer(),
                    a.getNickName(),
                    a.getCreatedAt(),
                    answerHeartPort.countByAnswerId(a.getId()),
                    answerHeartPort.exists(a.getId(), userId)))
            .toList();
  }
}
