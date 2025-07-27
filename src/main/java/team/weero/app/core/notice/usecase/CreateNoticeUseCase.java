package team.weero.app.core.notice.usecase;

import lombok.RequiredArgsConstructor;
import team.weero.app.core.Usecase;
import team.weero.app.core.notice.dto.request.CreateNoticeRequest;
import team.weero.app.core.notice.service.NoticeService;

@Usecase
@RequiredArgsConstructor
public class CreateNoticeUseCase {

    private final NoticeService noticeService;

    public void execute(CreateNoticeRequest request, String accountId) {
        noticeService.createNotice(request, accountId);
    }
}
