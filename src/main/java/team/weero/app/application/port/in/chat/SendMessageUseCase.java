package team.weero.app.application.port.in.chat;

import team.weero.app.application.service.chat.dto.request.SendMessageRequest;
import team.weero.app.application.service.chat.dto.response.MessageResponse;
import team.weero.app.domain.chat.model.Message;

public interface SendMessageUseCase {
    MessageResponse execute(SendMessageRequest request, String accountId);
}
