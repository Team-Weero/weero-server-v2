package team.weero.app.adapter.out.chat.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.chat.entity.ChatRoomJpaEntity;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoomJpaEntity, UUID> {}
