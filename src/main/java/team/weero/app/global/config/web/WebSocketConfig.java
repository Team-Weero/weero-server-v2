package team.weero.app.global.config.web;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import team.weero.app.global.websocket.JwtHandshakeInterceptor;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

  private final team.weero.app.global.websocket.ChatWebSocketHandler chatWebSocketHandler;
  private final JwtHandshakeInterceptor jwtHandshakeInterceptor;

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry
        .addHandler(chatWebSocketHandler, "/ws/chat/{roomId}")
        .addInterceptors(jwtHandshakeInterceptor)
        .setAllowedOrigins("*");
  }
}
