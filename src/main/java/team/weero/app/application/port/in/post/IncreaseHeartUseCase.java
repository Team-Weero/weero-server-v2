package team.weero.app.application.port.in.post;

import java.util.UUID;

public interface IncreaseHeartUseCase {
    void execute(UUID postId, UUID userId);
}
