package team.weero.app.adapter.in.post;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.weero.app.adapter.in.post.dto.request.CreatePostRequest;
import team.weero.app.adapter.in.post.dto.response.GetAllPostResponse;
import team.weero.app.adapter.in.post.dto.response.GetPostResponse;

import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostWebAdapter {
    /**
     * 또래상담게시판 생성 조회 제거 업데이트
     */
    @PostMapping("/")
    public void create(CreatePostRequest request) {

    }

    /**
     * 전체 조회
     */
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public GetAllPostResponse getAll() {

    }

    /**
     * 선택 조회
     */
    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public GetPostResponse get(@PathVariable UUID postId) {

    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID postId) {

    }
}
