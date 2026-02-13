package team.weero.app.adapter.in.web.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import team.weero.app.application.port.in.post.dto.request.CreatePostCommand;

@Schema(description = "게시글 생성 요청")
public record CreatePostRequest(
    @Schema(
            description = "게시글 제목 (최대 100자)",
            example = "게시글 제목입니다",
            requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "제목은 필수 값입니다.")
        @Size(max = 100)
        String title,
    @Schema(
            description = "게시글 내용 (최대 10,000자)",
            example = "게시글 내용입니다",
            requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "내용은 필수 값입니다.")
        @Size(max = 10_000)
        String content) {

  public CreatePostCommand from(UUID userId) {
    return new CreatePostCommand(title, content, userId);
  }
}
