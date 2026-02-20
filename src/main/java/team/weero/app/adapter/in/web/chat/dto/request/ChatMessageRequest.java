package team.weero.app.adapter.in.web.chat.dto.request;

import java.util.UUID;

public record ChatMessageRequest(UUID senderId, String text) {}
