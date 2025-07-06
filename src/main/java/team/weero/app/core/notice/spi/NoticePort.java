package team.weero.app.core.notice.spi;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.weero.app.persistence.notice.entity.Notice;
import team.weero.app.persistence.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoticePort {
    Notice save(Notice notice);
    Optional<Notice> findById(UUID id);
    Page<Notice> findAll(Pageable pageable);
    List<Notice> findByUser(User user);
    void deleteById(UUID id);
}
