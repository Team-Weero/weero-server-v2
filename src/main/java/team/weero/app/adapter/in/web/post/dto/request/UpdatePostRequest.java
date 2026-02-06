package team.weero.app.adapter.in.web.post.dto.request;

import jakarta.validation.constraints.Size;

public record UpdatePostRequest(
    @Size(max = 100) String title, @Size(max = 10_000) String content) {}
