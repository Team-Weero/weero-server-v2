package team.weero.app.adapter.out.chat.repository;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.chat.entity.ChatMessageJpaEntity;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessageJpaEntity, UUID> {
  Page<ChatMessageJpaEntity> findByChatRoomId(UUID chatRoomId, Pageable pageable);
}
