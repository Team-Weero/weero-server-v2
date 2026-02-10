package team.weero.app.application.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.exception.post.PostNotFoundException;
import team.weero.app.application.port.in.post.IncreaseHeartUseCase;
import team.weero.app.application.port.out.post.GetPostPort;
import team.weero.app.application.port.out.post.SavePostPort;
import team.weero.app.domain.post.model.Post;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class IncreaseHeartService implements IncreaseHeartUseCase {

    private final GetPostPort getPostPort;
    private final SavePostPort savePostPort;

    @Override
    public void execute(UUID postId, UUID userId) {
        Post post = getPostPort.getById(postId).orElseThrow(() -> PostNotFoundException.INSTANCE);

        post.increaseHeartCount();

        savePostPort.save(post);
    }
}
