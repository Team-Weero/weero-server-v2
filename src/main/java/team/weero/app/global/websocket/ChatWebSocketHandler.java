package team.weero.app.global.websocket;

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

  @Override
  public void afterConnectionEstablished(WebSocketSession session) {
    UUID userId = (UUID) session.getAttributes().get("userId");
    if (userId == null) {
      closeSession(session, "Unauthorized");
      return;
    }

    UUID chatRoomId = extractRoomId(session);
    chatRooms.computeIfAbsent(chatRoomId, k -> ConcurrentHashMap.newKeySet()).add(session);
    sessionUserMap.put(session, userId);
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    UUID chatRoomId = extractRoomId(session);
    UUID senderId = sessionUserMap.get(session);

    if (senderId == null) {
      closeSession(session, "Unauthorized");
      return;
    }

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
                            .build());

    Set<WebSocketSession> sessions = chatRooms.get(chatRoomId);
    if (sessions == null) return;

    broadcast(sessions, responseJson, chatRoomId);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
    UUID chatRoomId = extractRoomId(session);

    Set<WebSocketSession> sessions = chatRooms.get(chatRoomId);
    if (sessions != null) {
      sessions.remove(session);
      if (sessions.isEmpty()) chatRooms.remove(chatRoomId);
    }

    sessionUserMap.remove(session);
  }

  @Override
  public void notify(UUID chatRoomId) {
    Set<WebSocketSession> sessions = chatRooms.get(chatRoomId);
    if (sessions == null) return;

    String json;
    try {
      json = objectMapper.writeValueAsString(Map.of("type", Status.COMPLETED.name()));
    } catch (Exception e) {
      return;
    }

    broadcast(sessions, json, chatRoomId);
  }

  private void broadcast(Set<WebSocketSession> sessions, String message, UUID chatRoomId) {
    for (WebSocketSession s : sessions) {
      if (!s.isOpen()) {
        cleanupSession(s, sessions, chatRoomId);
        continue;
      }

      try {
        s.sendMessage(new TextMessage(message));
      } catch (IOException e) {
        cleanupSession(s, sessions, chatRoomId);
      }
    }

    if (sessions.isEmpty()) {
      chatRooms.remove(chatRoomId);
    }
  }

  private void cleanupSession(WebSocketSession session, Set<WebSocketSession> sessions, UUID chatRoomId) {
    try {
      session.close(CloseStatus.SERVER_ERROR);
    } catch (IOException ignored) {
    }
    sessions.remove(session);
    sessionUserMap.remove(session);
    if (sessions.isEmpty()) {
      chatRooms.remove(chatRoomId);
    }
  }

  private UUID extractRoomId(WebSocketSession session) {
    try {
      String path = session.getUri().getPath();
      String id = path.substring(path.lastIndexOf("/") + 1);
      return UUID.fromString(id);
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid roomId");
    }
  }

  private void closeSession(WebSocketSession session, String reason) {
    try {
      session.close(CloseStatus.NOT_ACCEPTABLE.withReason(reason));
    } catch (IOException ignored) {
    }
  }
}
