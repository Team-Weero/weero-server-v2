package team.weero.app.adapter.in.web.notice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateNoticeRequest(
    @NotBlank @Size(max = 200) String title, @NotBlank @Size(max = 10_000) String content) {}
