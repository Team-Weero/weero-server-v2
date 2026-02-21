package team.weero.app.global.websocket;

import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import team.weero.app.application.port.out.auth.JwtPort;

@Component
@RequiredArgsConstructor
public class JwtHandshakeInterceptor implements HandshakeInterceptor {

  private final JwtPort jwtPort;

  @Override
  public boolean beforeHandshake(
          ServerHttpRequest request,
          ServerHttpResponse response,
          WebSocketHandler wsHandler,
          Map<String, Object> attributes) {

    String token = extractToken(request);
    if (token == null) return false;

    try {
      var payload = jwtPort.parseToken(token);
      UUID userId = payload.userId();
      if (userId == null) return false;

      UUID roomId = extractRoomId(request);
      if (roomId == null) return false;

      attributes.put("userId", userId);
      attributes.put("roomId", roomId);
      return true;

    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public void afterHandshake(
          ServerHttpRequest request,
          ServerHttpResponse response,
          WebSocketHandler wsHandler,
          Exception exception) {}

  private String extractToken(ServerHttpRequest request) {
    String auth = request.getHeaders().getFirst("Authorization");
    if (auth == null || !auth.startsWith("Bearer ")) return null;
    return auth.substring(7);
  }

  private UUID extractRoomId(ServerHttpRequest request) {
    try {
      String path = request.getURI().getPath();
      String id = path.substring(path.lastIndexOf("/") + 1);
      return UUID.fromString(id);
    } catch (Exception e) {
      return null;
    }
  }
}
