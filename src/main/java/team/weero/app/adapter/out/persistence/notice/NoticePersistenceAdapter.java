package team.weero.app.adapter.out.persistence.notice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.persistence.notice.entity.NoticeJpaEntity;
import team.weero.app.adapter.out.persistence.notice.mapper.NoticeMapper;
import team.weero.app.adapter.out.persistence.notice.repository.NoticeJpaRepository;
import team.weero.app.adapter.out.persistence.user.entity.UserJpaEntity;
import team.weero.app.adapter.out.persistence.user.repository.UserJpaRepository;
import team.weero.app.application.port.out.notice.NoticePort;
import team.weero.app.domain.notice.model.Notice;

@Component
public class NoticePersistenceAdapter implements NoticePort {

  private final NoticeJpaRepository noticeJpaRepository;
  private final NoticeMapper noticeMapper;
  private final UserJpaRepository userRepository;

  public NoticePersistenceAdapter(
      NoticeJpaRepository noticeJpaRepository,
      NoticeMapper noticeMapper,
      UserJpaRepository userRepository) {
    this.noticeJpaRepository = noticeJpaRepository;
    this.noticeMapper = noticeMapper;
    this.userRepository = userRepository;
  }

  @Override
  public Notice save(Notice notice) {
    NoticeJpaEntity entity = noticeMapper.toEntity(notice);
    NoticeJpaEntity savedEntity = noticeJpaRepository.save(entity);
    return noticeMapper.toDomain(savedEntity);
  }

  @Override
  public Optional<Notice> findById(UUID id) {
    return noticeJpaRepository.findById(id).map(noticeMapper::toDomain);
  }

  @Override
  public Page<Notice> findAll(Pageable pageable) {
    return noticeJpaRepository.findAllByOrderByIdDesc(pageable).map(noticeMapper::toDomain);
  }

  @Override
  public List<Notice> findByUserId(UUID userId) {
    UserJpaEntity user =
        userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    return noticeJpaRepository.findByUserOrderByIdDesc(user).stream()
        .map(noticeMapper::toDomain)
        .toList();
  }

  @Override
  public void deleteById(UUID id) {
    noticeJpaRepository.deleteById(id);
  }
}
