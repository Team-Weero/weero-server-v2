package team.weero.app.application.port.out.notice;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.weero.app.domain.notice.Notice;

public interface LoadNoticePort {

  Optional<Notice> loadById(UUID id);

  Page<Notice> loadAll(Pageable pageable);
}
