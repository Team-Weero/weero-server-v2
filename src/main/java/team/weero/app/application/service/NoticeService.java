package team.weero.app.application.service;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.exception.notice.NoticeNotFoundException;
import team.weero.app.application.port.in.CreateNoticeCommand;
import team.weero.app.application.port.in.CreateNoticeUseCase;
import team.weero.app.application.port.in.DeleteNoticeUseCase;
import team.weero.app.application.port.in.GetCurrentUserUseCase;
import team.weero.app.application.port.in.GetNoticeListUseCase;
import team.weero.app.application.port.in.GetNoticeUseCase;
import team.weero.app.application.port.in.NoticeListResponse;
import team.weero.app.application.port.in.NoticeResponse;
import team.weero.app.application.port.in.UpdateNoticeCommand;
import team.weero.app.application.port.in.UpdateNoticeUseCase;
import team.weero.app.application.port.out.CheckNoticeOwnerPort;
import team.weero.app.application.port.out.DeleteNoticePort;
import team.weero.app.application.port.out.LoadNoticePort;
import team.weero.app.application.port.out.SaveNoticePort;
import team.weero.app.domain.auth.AuthUser;
import team.weero.app.domain.notice.Notice;
import team.weero.app.global.common.exception.ForbiddenException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService
    implements CreateNoticeUseCase,
        UpdateNoticeUseCase,
        DeleteNoticeUseCase,
        GetNoticeUseCase,
        GetNoticeListUseCase {

  private final SaveNoticePort saveNoticePort;
  private final LoadNoticePort loadNoticePort;
  private final DeleteNoticePort deleteNoticePort;
  private final CheckNoticeOwnerPort checkNoticeOwnerPort;
  private final GetCurrentUserUseCase getCurrentUserUseCase;

  @Override
  @Transactional
  public NoticeResponse create(CreateNoticeCommand command) {
    AuthUser currentUser = getCurrentUserUseCase.execute();

    Notice notice =
        new Notice(
            null, command.title(), command.content(), currentUser.getId(), LocalDateTime.now());

    Notice savedNotice = saveNoticePort.save(notice);

    return new NoticeResponse(
        savedNotice.getId(),
        savedNotice.getTitle(),
        savedNotice.getContent(),
        savedNotice.getWriterId(),
        savedNotice.getCreatedAt());
  }

  @Override
  @Transactional
  public NoticeResponse update(UpdateNoticeCommand command) {
    AuthUser currentUser = getCurrentUserUseCase.execute();

    if (!checkNoticeOwnerPort.isOwner(command.id(), currentUser.getId())) {
      throw new ForbiddenException();
    }

    Notice existingNotice =
        loadNoticePort.loadById(command.id()).orElseThrow(NoticeNotFoundException::new);

    Notice updatedNotice =
        new Notice(
            existingNotice.getId(),
            command.title(),
            command.content(),
            existingNotice.getWriterId(),
            existingNotice.getCreatedAt());

    Notice savedNotice = saveNoticePort.save(updatedNotice);

    return new NoticeResponse(
        savedNotice.getId(),
        savedNotice.getTitle(),
        savedNotice.getContent(),
        savedNotice.getWriterId(),
        savedNotice.getCreatedAt());
  }

  @Override
  @Transactional
  public void delete(UUID noticeId) {
    AuthUser currentUser = getCurrentUserUseCase.execute();

    if (!checkNoticeOwnerPort.isOwner(noticeId, currentUser.getId())) {
      throw new ForbiddenException();
    }

    deleteNoticePort.delete(noticeId);
  }

  @Override
  public NoticeResponse getById(UUID noticeId) {
    Notice notice = loadNoticePort.loadById(noticeId).orElseThrow(NoticeNotFoundException::new);

    return new NoticeResponse(
        notice.getId(),
        notice.getTitle(),
        notice.getContent(),
        notice.getWriterId(),
        notice.getCreatedAt());
  }

  @Override
  public NoticeListResponse getList(int page, int size) {
    PageRequest pageRequest = PageRequest.of(page, size);
    Page<Notice> noticePage = loadNoticePort.loadAll(pageRequest);

    return new NoticeListResponse(
        noticePage.getContent().stream()
            .map(
                notice ->
                    new NoticeResponse(
                        notice.getId(),
                        notice.getTitle(),
                        notice.getContent(),
                        notice.getWriterId(),
                        notice.getCreatedAt()))
            .toList(),
        noticePage.getTotalPages(),
        noticePage.getNumber());
  }
}
