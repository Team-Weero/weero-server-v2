package team.weero.app.core.notice.usecase;

import lombok.RequiredArgsConstructor;
import team.weero.app.core.Usecase;
import team.weero.app.core.notice.dto.response.NoticeResponse;
import team.weero.app.core.notice.service.NoticeService;

import java.util.UUID;

@Usecase
@RequiredArgsConstructor
public class GetNoticeByIdUseCase {

    private final NoticeService noticeService;

    public NoticeResponse execute(UUID id) {
        return noticeService.getNoticeById(id);
    }
}
