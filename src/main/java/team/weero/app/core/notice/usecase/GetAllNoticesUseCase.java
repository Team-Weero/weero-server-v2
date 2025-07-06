package team.weero.app.core.notice.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.weero.app.core.Usecase;
import team.weero.app.core.notice.dto.response.NoticeResponse;
import team.weero.app.core.notice.service.NoticeService;

@Usecase
@RequiredArgsConstructor
public class GetAllNoticesUseCase {

    private final NoticeService noticeService;

    public Page<NoticeResponse> execute(Pageable pageable) {
        return noticeService.getAllNotices(pageable);
    }
}
