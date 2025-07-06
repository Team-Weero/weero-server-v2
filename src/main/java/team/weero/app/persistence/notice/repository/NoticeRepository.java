package team.weero.app.persistence.notice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import team.weero.app.persistence.notice.entity.Notice;
import team.weero.app.persistence.user.entity.User;

import java.util.List;
import java.util.UUID;

public interface NoticeRepository extends JpaRepository<Notice, UUID> {
    Page<Notice> findAllByOrderByIdDesc(Pageable pageable);
    List<Notice> findByUserOrderByIdDesc(User user);
}
