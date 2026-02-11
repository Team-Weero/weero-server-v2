package team.weero.app.application.port.in.notice.dto.response;

import java.util.List;

public record NoticeListInfo(List<NoticeInfo> notices, int totalPages, int currentPage) {}
