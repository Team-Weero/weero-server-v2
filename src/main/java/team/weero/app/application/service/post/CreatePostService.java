package team.weero.app.application.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.weero.app.adapter.in.post.dto.request.CreatePostRequest;
import team.weero.app.adapter.out.post.entity.PostJpaEntity;
import team.weero.app.adapter.out.post.repository.PostRepository;
import team.weero.app.application.port.in.post.CreatePostUseCase;

@RequiredArgsConstructor
@Service
public class CreatePostService implements CreatePostUseCase {

    private final PostRepository postRepository;

    @Override
    public void createPost(CreatePostRequest request) {
        PostJpaEntity post = PostJpaEntity.builder()
                .title(request.title())
                .content(request.content())
                .build();

        postRepository.save(post);
    }
}
