package team.weero.app.application.port.out.chat;

import team.weero.app.domain.chat.ChatMessage;

public interface SaveMessagePort {
  ChatMessage save(ChatMessage chatMessage);
}
