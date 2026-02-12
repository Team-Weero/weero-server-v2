package team.weero.app.adapter.out.chat.entity;

import jakarta.persistence.*;
import team.weero.app.domain.BaseTimeEntity;
import team.weero.app.adapter.out.user.entity.UserJpaEntity;

@Entity
@Table(name = "tbl_message")
public class ChatMessageJpaEntity extends BaseTimeEntity {

  @Column(nullable = false, columnDefinition = "VARCHAR(1000)")
  private String text;

  @Column(nullable = false)
  private Boolean readStatus;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private UserJpaEntity user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "chat_room_id", nullable = false)
  private ChatRoomJpaEntity chatRoom;
}
