package team.weero.app.application.port.in.post;

import team.weero.app.adapter.in.web.post.dto.request.UpdatePostRequest;

import java.util.UUID;

public interface UpdatePostUseCase {
    void execute(UUID postId, UpdatePostRequest request);
}
