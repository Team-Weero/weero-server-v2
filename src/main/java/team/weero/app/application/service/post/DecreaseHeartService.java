package team.weero.app.application.service.post;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.exception.post.PostNotFoundException;
import team.weero.app.application.port.in.post.DecreaseHeartUseCase;
import team.weero.app.application.port.out.post.GetPostPort;
import team.weero.app.application.port.out.post.SavePostPort;
import team.weero.app.domain.post.model.Post;

@Service
@RequiredArgsConstructor
@Transactional
public class DecreaseHeartService implements DecreaseHeartUseCase {

  private final GetPostPort getPostPort;
  private final SavePostPort savePostPort;

  @Override
  public void execute(UUID postId, UUID userId) {
    Post post = getPostPort.getById(postId).orElseThrow(() -> PostNotFoundException.INSTANCE);

    post.decreaseHeartCount();

    savePostPort.save(post);
  }
}
