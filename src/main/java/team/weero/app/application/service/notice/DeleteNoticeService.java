package team.weero.app.application.service.notice;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.port.in.notice.DeleteNoticeUseCase;
import team.weero.app.application.port.in.notice.GetCurrentUserUseCase;
import team.weero.app.application.port.in.user.dto.response.UserInfo;
import team.weero.app.application.port.out.notice.CheckNoticeOwnerPort;
import team.weero.app.application.port.out.notice.DeleteNoticePort;
import team.weero.app.global.common.exception.ForbiddenException;

@Service
@RequiredArgsConstructor
public class DeleteNoticeService implements DeleteNoticeUseCase {

  private final DeleteNoticePort deleteNoticePort;
  private final CheckNoticeOwnerPort checkNoticeOwnerPort;
  private final GetCurrentUserUseCase getCurrentUserUseCase;

  @Override
  @Transactional
  public void execute(UUID noticeId) {
    UserInfo currentUser = getCurrentUserUseCase.execute();

    if (!checkNoticeOwnerPort.isOwner(noticeId, currentUser.id())) {
      throw new ForbiddenException();
    }

    deleteNoticePort.delete(noticeId);
  }
}
