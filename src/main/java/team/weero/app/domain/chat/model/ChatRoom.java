package team.weero.app.domain.chat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ChatRoom {
    private UUID id;
    private UUID startUserId;
    private UUID receiverUserId;

    
    public boolean hasParticipant(UUID userId) {
        return startUserId.equals(userId) || receiverUserId.equals(userId);
    }

    public UUID getOtherParticipant(UUID userId) {
        if (startUserId.equals(userId)) {
            return receiverUserId;
        } else if (receiverUserId.equals(userId)) {
            return startUserId;
        }
        throw new IllegalArgumentException("User is not a participant of this chat room");
    }
}
