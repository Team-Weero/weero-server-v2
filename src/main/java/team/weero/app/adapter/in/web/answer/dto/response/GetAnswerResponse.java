package team.weero.app.adapter.in.web.answer.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import team.weero.app.domain.answer.model.Answer;

public record GetAnswerResponse(List<AnswerDto> answers) {

  public record AnswerDto(UUID id, String answer, String nickName, LocalDateTime createdAt) {}

  public static GetAnswerResponse from(List<Answer> domainAnswers) {
    List<AnswerDto> answerDto =
        domainAnswers.stream()
            .map(a -> new AnswerDto(a.getId(), a.getAnswer(), a.getNickName(), a.getCreatedAt()))
            .toList();
    return new GetAnswerResponse(answerDto);
  }
}
