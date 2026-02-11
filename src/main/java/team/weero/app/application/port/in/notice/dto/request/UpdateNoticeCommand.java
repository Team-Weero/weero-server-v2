package team.weero.app.application.port.in.notice.dto.request;

import java.util.UUID;

public record UpdateNoticeCommand(UUID id, String title, String content) {}
