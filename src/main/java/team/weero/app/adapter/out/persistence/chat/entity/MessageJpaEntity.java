package team.weero.app.adapter.out.persistence.chat.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import team.weero.app.adapter.out.persistence.user.entity.UserJpaEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "tbl_message")
public class MessageJpaEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoomJpaEntity chatRoom;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserJpaEntity sender;

    @Column(nullable = false, length = 255)
    private String text;

    @Column(nullable = false)
    private LocalDateTime sendDate;

    @Column(nullable = false)
    private boolean readStatus;
}
