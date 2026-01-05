package team.weero.app.adapter.out.persistence.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team.weero.app.adapter.out.persistence.chat.entity.ChatRoomJpaEntity;
import team.weero.app.adapter.out.persistence.user.entity.UserJpaEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatRoomJpaRepository extends JpaRepository<ChatRoomJpaEntity, UUID> {

    @Query("SELECT c FROM ChatRoomJpaEntity c WHERE c.startUser.id = :userId OR c.receiverUser.id = :userId")
    List<ChatRoomJpaEntity> findByUserId(@Param("userId") UUID userId);

    @Query("SELECT c FROM ChatRoomJpaEntity c WHERE " +
           "(c.startUser = :user1 AND c.receiverUser = :user2) OR " +
           "(c.startUser = :user2 AND c.receiverUser = :user1)")
    Optional<ChatRoomJpaEntity> findByParticipants(
        @Param("user1") UserJpaEntity user1,
        @Param("user2") UserJpaEntity user2
    );
}
