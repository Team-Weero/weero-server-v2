package team.weero.app.adapter.in.web.answer.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAnswerRequest(
    @NotBlank(message = "댓글은 필수 값입니다.") @Size(max = 1000) String answer) {}
