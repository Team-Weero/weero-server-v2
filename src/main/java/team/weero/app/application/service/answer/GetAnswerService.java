package team.weero.app.application.service.answer;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.port.in.answer.GetAnswerUseCase;
import team.weero.app.application.port.in.answer.dto.response.GetAnswerInfo;
import team.weero.app.application.port.out.answer.GetAnswerPort;

@Service
@RequiredArgsConstructor
public class GetAnswerService implements GetAnswerUseCase {

  private final GetAnswerPort getAnswerPort;

  @Transactional(readOnly = true)
  @Override
  public List<GetAnswerInfo> execute(UUID postId) {
    return getAnswerPort.getAll(postId).stream()
        .map(a -> new GetAnswerInfo(a.getId(), a.getAnswer(), a.getNickName(), a.getCreatedAt()))
        .toList();
  }
}
