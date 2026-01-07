package team.weero.app.adapter.out.answer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import team.weero.app.global.entity.BaseTimeEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_answer")
public class AnswerJpaEntity extends BaseTimeEntity {

    @Column(nullable = false, columnDefinition = "VARCHAR(1000)")
    private String answer;

    private LocalDateTime deletedTime;

    public void markDeleted() {
        this.deletedTime = LocalDateTime.now();
    }
}
