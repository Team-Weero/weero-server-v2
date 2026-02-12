package team.weero.app.application.service.notice;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.port.in.notice.CreateNoticeUseCase;
import team.weero.app.application.port.in.user.GetCurrentUserUseCase;
import team.weero.app.application.port.in.notice.dto.request.CreateNoticeCommand;
import team.weero.app.application.port.in.notice.dto.response.NoticeInfo;
import team.weero.app.application.port.in.user.dto.response.UserInfo;
import team.weero.app.application.port.out.notice.SaveNoticePort;
import team.weero.app.domain.notice.Notice;

@Service
@RequiredArgsConstructor
public class CreateNoticeService implements CreateNoticeUseCase {

  private final GetCurrentUserUseCase getCurrentUserUseCase;
  private final SaveNoticePort saveNoticePort;

  @Override
  @Transactional
  public NoticeInfo execute(CreateNoticeCommand command) {
    UserInfo currentUser = getCurrentUserUseCase.execute();

    Notice notice =
        new Notice(null, command.title(), command.content(), currentUser.id(), LocalDateTime.now());

    Notice savedNotice = saveNoticePort.save(notice);

    return new NoticeInfo(
        savedNotice.getId(),
        savedNotice.getTitle(),
        savedNotice.getContent(),
        savedNotice.getWriterId(),
        savedNotice.getCreatedAt());
  }
}
