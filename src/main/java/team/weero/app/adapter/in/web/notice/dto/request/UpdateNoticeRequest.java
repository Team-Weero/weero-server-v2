package team.weero.app.adapter.in.web.notice.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateNoticeRequest(@NotBlank String title, @NotBlank String content) {}
