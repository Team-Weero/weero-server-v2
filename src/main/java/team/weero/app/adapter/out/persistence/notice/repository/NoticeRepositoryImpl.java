package team.weero.app.adapter.out.persistence.notice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import team.weero.app.domain.notice.model.Notice;
import team.weero.app.application.port.out.notice.NoticeRepository;
import team.weero.app.adapter.out.persistence.notice.entity.NoticeJpaEntity;
import team.weero.app.adapter.out.persistence.notice.mapper.NoticeMapper;
import team.weero.app.adapter.out.persistence.user.entity.UserJpaEntity;
import team.weero.app.adapter.out.persistence.user.repository.UserJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class NoticeRepositoryImpl implements NoticeRepository {

    private final NoticeJpaRepository noticeJpaRepository;
    private final NoticeMapper noticeMapper;
    private final UserJpaRepository userRepository;

    public NoticeRepositoryImpl(NoticeJpaRepository noticeJpaRepository, NoticeMapper noticeMapper, UserJpaRepository userRepository) {
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
        return noticeJpaRepository.findById(id)
                .map(noticeMapper::toDomain);
    }

    @Override
    public Page<Notice> findAll(Pageable pageable) {
        return noticeJpaRepository.findAllByOrderByIdDesc(pageable)
                .map(noticeMapper::toDomain);
    }

    @Override
    public List<Notice> findByUserId(UUID userId) {
        UserJpaEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return noticeJpaRepository.findByUserOrderByIdDesc(user).stream()
                .map(noticeMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        noticeJpaRepository.deleteById(id);
    }
}
