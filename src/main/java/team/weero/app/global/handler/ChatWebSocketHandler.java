package team.weero.app.global.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import team.weero.app.adapter.in.web.chat.dto.request.ChatMessageRequest;
import team.weero.app.adapter.in.web.chat.dto.response.ChatMessageResponse;
import team.weero.app.application.port.out.auth.JwtPort;
import team.weero.app.application.port.out.chat.NotifyCounselClosedPort;
import team.weero.app.application.port.out.chat.SaveMessagePort;
import team.weero.app.domain.chat.ChatMessage;
import team.weero.app.domain.counsel.type.Status;

@Component
@RequiredArgsConstructor
public class ChatWebSocketHandler extends TextWebSocketHandler implements NotifyCounselClosedPort {

  private final Map<UUID, Set<WebSocketSession>> chatRooms = new ConcurrentHashMap<>();
  private final Map<WebSocketSession, UUID> sessionUserMap = new ConcurrentHashMap<>();
  private final ObjectMapper objectMapper;
  private final SaveMessagePort saveMessagePort;
  private final JwtPort jwtPort;

  @Override
  public void afterConnectionEstablished(WebSocketSession session) {
    String token = getToken(session);
    UUID userId = jwtPort.parseToken(token).userId();

    UUID chatRoomId = getChatRoomId(session);
    chatRooms.computeIfAbsent(chatRoomId, k -> ConcurrentHashMap.newKeySet()).add(session);
    sessionUserMap.put(session, userId);
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    UUID chatRoomId = getChatRoomId(session);
    UUID senderId = sessionUserMap.get(session);
    ChatMessageRequest request =
        objectMapper.readValue(message.getPayload(), ChatMessageRequest.class);

    ChatMessage saved =
        saveMessagePort.save(
            ChatMessage.builder()
                .chatRoomId(chatRoomId)
                .senderId(senderId)
                .text(request.text())
                .build());

    String responseJson =
        objectMapper.writeValueAsString(
            ChatMessageResponse.builder()
                .messageId(saved.getId())
                .senderId(saved.getSenderId())
                .text(saved.getText())
                .sendDate(saved.getSendDate())
                .readStatus(saved.getReadStatus())
                .build());

    for (WebSocketSession s : chatRooms.get(chatRoomId)) {
      if (s.isOpen()) {
        s.sendMessage(new TextMessage(responseJson));
      }
    }
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
    UUID chatRoomId = getChatRoomId(session);
    chatRooms.get(chatRoomId).remove(session);
    sessionUserMap.remove(session);
  }

  private String getToken(WebSocketSession session) {
    String query = session.getUri().getQuery();
    return query.substring(query.indexOf("=") + 1);
  }

  private UUID getChatRoomId(WebSocketSession session) {
    String path = session.getUri().getPath();
    return UUID.fromString(path.substring(path.lastIndexOf("/") + 1));
  }

  @Override
  public void notify(UUID chatRoomId) throws IOException {
    Set<WebSocketSession> sessions = chatRooms.get(chatRoomId);
    if (sessions == null) return;

    String json = objectMapper.writeValueAsString(Map.of("type", Status.COMPLETED.name()));
    for (WebSocketSession s : sessions) {
      if (s.isOpen()) {
        s.sendMessage(new TextMessage(json));
      }
    }
  }
}
