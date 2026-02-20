package team.weero.app.global.websocket;

import java.util.Arrays;
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

    String token = extractToken(request.getURI().getQuery());

    if (token == null) return false;

    try {
      UUID userId = jwtPort.parseToken(token).userId();
      attributes.put("userId", userId);
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

  private String extractToken(String query) {
    if (query == null) return null;

    return Arrays.stream(query.split("&"))
        .map(param -> param.split("="))
        .filter(pair -> pair.length == 2 && pair[0].equals("token"))
        .map(pair -> pair[1])
        .findFirst()
        .orElse(null);
  }
}
