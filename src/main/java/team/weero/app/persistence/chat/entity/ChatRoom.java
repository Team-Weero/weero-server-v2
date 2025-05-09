package team.weero.app.persistence.chat.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import team.weero.app.persistence.user.entity.User;

import java.util.UUID;

@Entity
@Table(name = "tbl_chat_room")
public class ChatRoom {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "start_user_id")
    private User startUser;

    @ManyToOne
    @JoinColumn(name = "receiver_user_id")
    private User receiverUser;
}