package team.weero.app.adapter.in.web.chat.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import team.weero.app.application.port.in.chat.dto.response.ChatMessageInfo;

/** 필요한 정보만 포함하도록 Page -> List로 변환하여 반환함 */
@Schema(description = "채팅 메시지 목록 응답")
public record ChatMessageListResponse(
    @Schema(description = "채팅 메시지 목록") List<ChatMessageResponse> messages,
    @Schema(description = "전체 페이지 수") int totalPages,
    @Schema(description = "현재 페이지") int currentPage) {

  public static ChatMessageListResponse from(Page<ChatMessageInfo> page) {
    List<ChatMessageResponse> messageResponses =
        page.getContent().stream().map(ChatMessageResponse::from).collect(Collectors.toList());

    return new ChatMessageListResponse(messageResponses, page.getTotalPages(), page.getNumber());
  }
}
