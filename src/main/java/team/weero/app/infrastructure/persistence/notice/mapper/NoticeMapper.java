package team.weero.app.infrastructure.persistence.notice.mapper;

import org.springframework.stereotype.Component;
import team.weero.app.domain.notice.model.Notice;
import team.weero.app.infrastructure.persistence.notice.entity.NoticeJpaEntity;
import team.weero.app.infrastructure.persistence.user.entity.UserJpaEntity;
import team.weero.app.infrastructure.persistence.user.repository.UserJpaRepository;

@Component
public class NoticeMapper {

    private final UserJpaRepository userRepository;

    public NoticeMapper(UserJpaRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Notice toDomain(NoticeJpaEntity entity) {
        return Notice.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .title(entity.getTitle())
                .contents(entity.getContents())
                .build();
    }

    public NoticeJpaEntity toEntity(Notice domain) {
        UserJpaEntity user = userRepository.findById(domain.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return NoticeJpaEntity.builder()
                .id(domain.getId())
                .user(user)
                .title(domain.getTitle())
                .contents(domain.getContents())
                .build();
    }
}
