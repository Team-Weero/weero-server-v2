package team.weero.app.application.service.post;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.weero.app.adapter.in.web.post.dto.request.UpdatePostRequest;
import team.weero.app.application.port.in.post.UpdatePostUseCase;
import team.weero.app.application.port.out.post.UpdatePostPort;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdatePostService implements UpdatePostUseCase {

    private final UpdatePostPort updatePostPort;

    @Override
    public void execute(UUID postId, UpdatePostRequest request) {
        updatePostPort.update(postId, request.title(), request.content());
    }
}
