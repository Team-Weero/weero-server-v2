package team.weero.app.core.notice.usecase;

import lombok.RequiredArgsConstructor;
import team.weero.app.core.Usecase;
import team.weero.app.core.notice.service.NoticeService;

import java.util.UUID;

@Usecase
@RequiredArgsConstructor
public class DeleteNoticeUseCase {

    private final NoticeService noticeService;

    public void execute(UUID id, String accountId) {
        noticeService.deleteNotice(id, accountId);
    }
}
