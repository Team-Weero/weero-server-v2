package team.weero.app.adapter.in.web.chat.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "채팅 메시지 요청")
public record ChatMessageRequest(@Schema(description = "메시지 내용", example = "안녕하세요") String text) {}
