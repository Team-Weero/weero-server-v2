package team.weero.app.adapter.in.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePostRequest(
    @NotBlank(message = "제목은 필수 값입니다.") @Size(max = 100) String title,
    @NotBlank(message = "내용은 필수 값입니다.") @Size(max = 10_000) String content) {}
