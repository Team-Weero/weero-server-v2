package team.weero.app.application.port.out.notice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.weero.app.domain.notice.model.Notice;

public interface NoticePort {
  Notice save(Notice notice);

  Optional<Notice> findById(UUID id);

  Page<Notice> findAll(Pageable pageable);

  List<Notice> findByUserId(UUID userId);

  void deleteById(UUID id);
}
