package team.weero.app.adapter.in.web.chat.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "채팅 메시지 요청")
public record ChatMessageRequest(
    @Schema(description = "메시지 내용", example = "안녕하세요")
        @NotBlank(message = "text 메시지는 필수 값입니다.")
        @Size(max = 1000, message = "메시지는 1000자 이하여야 합니다.")
        String text) {}
