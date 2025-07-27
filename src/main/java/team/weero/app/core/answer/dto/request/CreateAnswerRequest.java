package team.weero.app.core.answer.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreateAnswerRequest(
    @NotNull(message = "고민 ID는 필수입니다")
    UUID concernId,
    
    @NotBlank(message = "답변 내용은 필수입니다")
    @Size(max = 2000, message = "답변 내용은 2000자 이하여야 합니다")
    String content
) {
}
