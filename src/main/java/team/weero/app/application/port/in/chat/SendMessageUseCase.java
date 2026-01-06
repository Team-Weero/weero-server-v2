package team.weero.app.application.port.in.chat;

import team.weero.app.application.service.chat.dto.request.SendMessageRequest;
import team.weero.app.application.service.chat.dto.response.MessageResponse;

public interface SendMessageUseCase {
  MessageResponse execute(SendMessageRequest request, String accountId);
}
