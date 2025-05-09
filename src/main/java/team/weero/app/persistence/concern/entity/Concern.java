package team.weero.app.persistence.concern.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import team.weero.app.persistence.user.entity.User;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tbl_concern")
public class Concern {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 10000)
    private String contents;

    @Column(nullable = false)
    private LocalDate date;
}