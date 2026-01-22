package team.weero.app.adapter.in.web.answer.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import team.weero.app.domain.answer.model.Answer;

public record GetAnswerResponse(UUID id, String Answer, String nickName, LocalDateTime createdAt) {
  public static GetAnswerResponse from(Answer answer) {
    return new GetAnswerResponse(answer.getId() , answer.getAnswer(), answer.getNickName(), answer.getCreatedAt());
  }
}
