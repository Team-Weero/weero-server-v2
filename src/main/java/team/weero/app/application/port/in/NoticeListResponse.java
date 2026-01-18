package team.weero.app.application.port.in;

import java.util.List;

public record NoticeListResponse(List<NoticeResponse> notices, int totalPages, int currentPage) {}
