package team.weero.app.adapter.out.persistence.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team.weero.app.adapter.out.persistence.chat.entity.ChatRoomJpaEntity;
import team.weero.app.adapter.out.persistence.chat.entity.MessageJpaEntity;

import java.util.List;
import java.util.UUID;

public interface MessageJpaRepository extends JpaRepository<MessageJpaEntity, UUID> {

    List<MessageJpaEntity> findByChatRoom(ChatRoomJpaEntity chatRoom);

    List<MessageJpaEntity> findByChatRoomOrderBySendDateAsc(ChatRoomJpaEntity chatRoom);

    @Query("SELECT m FROM MessageJpaEntity m WHERE m.chatRoom = :chatRoom AND m.readStatus = false")
    List<MessageJpaEntity> findUnreadMessagesByChatRoom(@Param("chatRoom") ChatRoomJpaEntity chatRoom);

    @Query("SELECT COUNT(m) FROM MessageJpaEntity m WHERE m.chatRoom.id = :chatRoomId AND m.sender.id != :userId AND m.readStatus = false")
    int countUnreadMessagesByChatRoomIdAndReceiverId(
        @Param("chatRoomId") UUID chatRoomId,
        @Param("userId") UUID userId
    );
}
