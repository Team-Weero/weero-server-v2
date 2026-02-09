package team.weero.app.adapter.in.web.answer.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "답변 생성 요청")
public record CreateAnswerRequest(
    @Schema(
            description = "답변 내용 (최대 1,000자)",
            example = "답변 내용입니다",
            requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "댓글은 필수 값입니다.")
        @Size(max = 1000)
        String answer) {}
