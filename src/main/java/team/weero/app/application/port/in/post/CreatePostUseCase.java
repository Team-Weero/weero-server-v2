package team.weero.app.application.port.in.post;

import team.weero.app.adapter.in.post.dto.request.CreatePostRequest;

public interface CreatePostUseCase {

    void createPost(CreatePostRequest request);
}
