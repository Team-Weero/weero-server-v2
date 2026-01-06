package team.weero.app.adapter.out.persistence.notice.entity;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import team.weero.app.adapter.out.persistence.user.entity.UserJpaEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "tbl_notice")
public class NoticeJpaEntity {
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserJpaEntity user;

  @Column(nullable = false, length = 255)
  private String title;

  @Column(nullable = false, length = 10000)
  private String contents;
}
