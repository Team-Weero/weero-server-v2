package team.weero.app.infrastructure.persistence.chat.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import team.weero.app.infrastructure.persistence.user.entity.UserJpaEntity;

import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "tbl_chat_room")
public class ChatRoomJpaEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "start_user_id")
    private UserJpaEntity startUser;

    @ManyToOne
    @JoinColumn(name = "receiver_user_id")
    private UserJpaEntity receiverUser;
}
