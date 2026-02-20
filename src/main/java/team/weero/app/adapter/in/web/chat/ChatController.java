package team.weero.app.adapter.in.web.chat;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.weero.app.adapter.in.web.chat.dto.response.ChatMessageResponse;
import team.weero.app.application.port.in.chat.GetChatMessagesUseCase;

@Tag(name = "Chat", description = "채팅 API")
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

  private final GetChatMessagesUseCase getChatMessagesUseCase;

  @Operation(summary = "메시지 내역 조회", description = "채팅방의 이전 메시지를 조회합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "조회 성공"),
    @ApiResponse(responseCode = "401", description = "인증 실패"),
    @ApiResponse(responseCode = "404", description = "채팅방을 찾을 수 없음")
  })
  @SecurityRequirement(name = "bearer-key")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/rooms/{chatRoomId}/messages")
  public List<ChatMessageResponse> getChatMessages(@PathVariable UUID chatRoomId) {
    return getChatMessagesUseCase.execute(chatRoomId).stream()
        .map(ChatMessageResponse::from)
        .toList();
  }
}
