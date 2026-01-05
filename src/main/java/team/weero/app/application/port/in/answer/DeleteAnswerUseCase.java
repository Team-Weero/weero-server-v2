package team.weero.app.application.port.in.answer;

import java.util.UUID;

public interface DeleteAnswerUseCase {
    void execute(UUID answerId, String accountId);
}
