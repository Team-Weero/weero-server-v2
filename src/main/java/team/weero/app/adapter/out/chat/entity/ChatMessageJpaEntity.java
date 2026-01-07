package team.weero.app.adapter.out.chat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import team.weero.app.global.entity.BaseTimeEntity;

@Entity
@Table(name = "tbl_message")
public class ChatMessageJpaEntity extends BaseTimeEntity {

    @Column(nullable = false, columnDefinition = "VARCHAR(1000)")
    private String text;

    @Column(nullable = false)
    private Boolean readStatus;
}
