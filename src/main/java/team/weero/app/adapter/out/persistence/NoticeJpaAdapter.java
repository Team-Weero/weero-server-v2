package team.weero.app.adapter.out.persistence;

import jakarta.persistence.EntityManager;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.notice.entity.NoticeJpaEntity;
import team.weero.app.adapter.out.notice.repository.NoticeRepository;
import team.weero.app.adapter.out.user.entity.UserJpaEntity;
import team.weero.app.application.port.out.CheckNoticeOwnerPort;
import team.weero.app.application.port.out.DeleteNoticePort;
import team.weero.app.application.port.out.LoadNoticePort;
import team.weero.app.application.port.out.SaveNoticePort;
import team.weero.app.domain.notice.Notice;
import team.weero.app.global.exception.NoticeNotFoundException;

@Component
@RequiredArgsConstructor
public class NoticeJpaAdapter
    implements SaveNoticePort, LoadNoticePort, DeleteNoticePort, CheckNoticeOwnerPort {

  private final NoticeRepository noticeRepository;
  private final EntityManager entityManager;

  @Override
  public Notice save(Notice notice) {
    NoticeJpaEntity entity;

    if (notice.getId() == null) {
      UserJpaEntity userEntity =
          entityManager.getReference(UserJpaEntity.class, notice.getWriterId());

      entity =
          NoticeJpaEntity.builder()
              .title(notice.getTitle())
              .content(notice.getContent())
              .user(userEntity)
              .build();
    } else {
      NoticeJpaEntity existing = noticeRepository.findById(notice.getId()).orElseThrow();
      entity =
          NoticeJpaEntity.builder()
              .id(existing.getId())
              .title(notice.getTitle())
              .content(notice.getContent())
              .user(existing.getUser())
              .deletedTime(existing.getDeletedTime())
              .build();
    }

    NoticeJpaEntity saved = noticeRepository.save(entity);
    return toDomain(saved);
  }

  @Override
  public Optional<Notice> loadById(UUID id) {
    return noticeRepository.findByIdAndDeletedTimeIsNull(id).map(this::toDomain);
  }

  @Override
  public Page<Notice> loadAll(Pageable pageable) {
    return noticeRepository.findAllByDeletedTimeIsNull(pageable).map(this::toDomain);
  }

  @Override
  public void delete(UUID id) {
    NoticeJpaEntity entity = noticeRepository.findByIdAndDeletedTimeIsNull(id).orElseThrow();
    entity.markDeleted();
    noticeRepository.save(entity);
  }

  @Override
  public boolean isOwner(UUID noticeId, UUID userId) {
    NoticeJpaEntity notice =
        noticeRepository
            .findByIdAndDeletedTimeIsNull(noticeId)
            .orElseThrow(NoticeNotFoundException::new);
    return notice.getUser().getId().equals(userId);
  }

  private Notice toDomain(NoticeJpaEntity entity) {
    return new Notice(
        entity.getId(),
        entity.getTitle(),
        entity.getContent(),
        entity.getUser().getId(),
        entity.getCreatedAt());
  }
}
