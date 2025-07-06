package team.weero.app.core.notice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.weero.app.core.notice.dto.request.CreateNoticeRequest;
import team.weero.app.core.notice.dto.request.UpdateNoticeRequest;
import team.weero.app.core.notice.dto.response.NoticeResponse;

import java.util.List;
import java.util.UUID;

public interface NoticeService {
    void createNotice(CreateNoticeRequest request, String accountId);
    NoticeResponse getNoticeById(UUID id);
    Page<NoticeResponse> getAllNotices(Pageable pageable);
    List<NoticeResponse> getMyNotices(String accountId);
    void updateNotice(UUID id, UpdateNoticeRequest request, String accountId);
    void deleteNotice(UUID id, String accountId);
}
