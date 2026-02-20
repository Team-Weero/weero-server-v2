package team.weero.app.adapter.out.chat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.weero.app.adapter.out.user.entity.UserJpaEntity;
import team.weero.app.domain.BaseTimeEntity;

@Entity
@Builder
@Getter
@Table(name = "tbl_message")
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageJpaEntity extends BaseTimeEntity {

  @Column(nullable = false, columnDefinition = "VARCHAR(1000)")
  private String text;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private UserJpaEntity user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "chat_room_id", nullable = false)
  private ChatRoomJpaEntity chatRoom;
}
