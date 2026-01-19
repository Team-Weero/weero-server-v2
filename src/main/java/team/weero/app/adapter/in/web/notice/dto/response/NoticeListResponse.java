package team.weero.app.adapter.in.web.notice.dto.response;

import java.util.List;

public record NoticeListResponse(List<NoticeResponse> notices, int totalPages, int currentPage) {}
