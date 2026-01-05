package team.weero.app.presentation.chat;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import team.weero.app.application.chat.dto.request.CreateChatRoomRequest;
import team.weero.app.application.chat.dto.request.SendMessageRequest;
import team.weero.app.application.chat.dto.response.ChatRoomResponse;
import team.weero.app.application.chat.dto.response.MessageResponse;
import team.weero.app.application.chat.usecase.CreateChatRoomUseCase;
import team.weero.app.application.chat.usecase.GetChatRoomMessagesUseCase;
import team.weero.app.application.chat.usecase.SendMessageUseCase;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final CreateChatRoomUseCase createChatRoomUseCase;
    private final SendMessageUseCase sendMessageUseCase;
    private final GetChatRoomMessagesUseCase getChatRoomMessagesUseCase;

    public ChatController(CreateChatRoomUseCase createChatRoomUseCase,
                         SendMessageUseCase sendMessageUseCase,
                         GetChatRoomMessagesUseCase getChatRoomMessagesUseCase) {
        this.createChatRoomUseCase = createChatRoomUseCase;
        this.sendMessageUseCase = sendMessageUseCase;
        this.getChatRoomMessagesUseCase = getChatRoomMessagesUseCase;
    }

    @PostMapping("/rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public ChatRoomResponse createChatRoom(@RequestBody @Valid CreateChatRoomRequest request,
                                          Authentication authentication) {
        return createChatRoomUseCase.execute(request, authentication.getName());
    }

    @PostMapping("/messages")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse sendMessage(@RequestBody @Valid SendMessageRequest request,
                                      Authentication authentication) {
        return sendMessageUseCase.execute(request, authentication.getName());
    }

    @GetMapping("/rooms/{chatRoomId}/messages")
    public List<MessageResponse> getChatRoomMessages(@PathVariable UUID chatRoomId,
                                                     Authentication authentication) {
        return getChatRoomMessagesUseCase.execute(chatRoomId, authentication.getName());
    }
}
