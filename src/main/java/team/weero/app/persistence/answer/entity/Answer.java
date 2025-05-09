package team.weero.app.persistence.answer.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import team.weero.app.persistence.concern.entity.Concern;

import java.util.UUID;

@Entity
@Table(name = "tbl_answer")
public class Answer {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Concern concern;

    @Column(nullable = false, length = 255)
    private String answer;
}