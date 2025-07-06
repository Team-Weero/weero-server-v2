package team.weero.app.persistence.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import team.weero.app.core.notice.spi.NoticePort;
import team.weero.app.persistence.notice.entity.Notice;
import team.weero.app.persistence.notice.repository.NoticeRepository;
import team.weero.app.persistence.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NoticePersistenceAdapter implements NoticePort {

    private final NoticeRepository noticeRepository;

    @Override
    public Notice save(Notice notice) {
        return noticeRepository.save(notice);
    }

    @Override
    public Optional<Notice> findById(UUID id) {
        return noticeRepository.findById(id);
    }

    @Override
    public Page<Notice> findAll(Pageable pageable) {
        return noticeRepository.findAllByOrderByIdDesc(pageable);
    }

    @Override
    public List<Notice> findByUser(User user) {
        return noticeRepository.findByUserOrderByIdDesc(user);
    }

    @Override
    public void deleteById(UUID id) {
        noticeRepository.deleteById(id);
    }
}
