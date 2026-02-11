package team.weero.app.application.port.in.answer.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetAnswerInfo(UUID id, String answer, String nickName, LocalDateTime createdAt) {}
