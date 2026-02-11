package team.weero.app.application.service.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.exception.notice.NoticeNotFoundException;
import team.weero.app.application.port.in.notice.GetCurrentUserUseCase;
import team.weero.app.application.port.in.notice.UpdateNoticeUseCase;
import team.weero.app.application.port.in.notice.dto.request.UpdateNoticeCommand;
import team.weero.app.application.port.in.notice.dto.response.NoticeInfo;
import team.weero.app.application.port.in.user.dto.response.UserInfo;
import team.weero.app.application.port.out.notice.CheckNoticeOwnerPort;
import team.weero.app.application.port.out.notice.LoadNoticePort;
import team.weero.app.application.port.out.notice.SaveNoticePort;
import team.weero.app.domain.notice.Notice;
import team.weero.app.global.common.exception.ForbiddenException;

@Service
@RequiredArgsConstructor
public class UpdateNoticeService implements UpdateNoticeUseCase {

  private final GetCurrentUserUseCase getCurrentUserUseCase;
  private final CheckNoticeOwnerPort checkNoticeOwnerPort;
  private final LoadNoticePort loadNoticePort;
  private final SaveNoticePort saveNoticePort;

  @Override
  @Transactional
  public NoticeInfo execute(UpdateNoticeCommand command) {
    UserInfo currentUser = getCurrentUserUseCase.execute();

    if (!checkNoticeOwnerPort.isOwner(command.id(), currentUser.id())) {
      throw new ForbiddenException();
    }

    Notice existingNotice =
        loadNoticePort.loadById(command.id()).orElseThrow(() -> NoticeNotFoundException.INSTANCE);

    Notice updatedNotice =
        new Notice(
            existingNotice.getId(),
            command.title(),
            command.content(),
            existingNotice.getWriterId(),
            existingNotice.getCreatedAt());

    Notice savedNotice = saveNoticePort.save(updatedNotice);

    return new NoticeInfo(
        savedNotice.getId(),
        savedNotice.getTitle(),
        savedNotice.getContent(),
        savedNotice.getWriterId(),
        savedNotice.getCreatedAt());
  }
}
