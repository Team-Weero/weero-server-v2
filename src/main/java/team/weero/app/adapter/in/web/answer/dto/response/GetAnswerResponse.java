package team.weero.app.adapter.in.web.answer.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import team.weero.app.application.port.in.answer.dto.response.GetAnswerInfo;

@Schema(description = "답변 목록 조회 응답")
public record GetAnswerResponse(@Schema(description = "답변 목록") List<AnswerDto> answers) {

  @Schema(description = "답변 항목")
  public record AnswerDto(
      @Schema(description = "답변 ID", example = "550e8400-e29b-41d4-a716-446655440000") UUID id,
      @Schema(description = "답변 내용", example = "답변 내용입니다") String answer,
      @Schema(description = "작성자 닉네임", example = "홍길동") String nickName,
      @Schema(description = "생성 일시", example = "2024-01-01T10:00:00") LocalDateTime createdAt) {}

  public static GetAnswerResponse from(List<GetAnswerInfo> infos) {
    List<AnswerDto> answerDto =
        infos.stream()
            .map(a -> new AnswerDto(a.id(), a.answer(), a.nickName(), a.createdAt()))
            .toList();
    return new GetAnswerResponse(answerDto);
  }
}
