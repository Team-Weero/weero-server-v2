package team.weero.app.application.port.in.answer.dto.request;

import java.util.UUID;

public record CreateAnswerCommand(String answer, UUID userId, UUID postId) {}
