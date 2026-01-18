package team.weero.app.domain.post.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Post {
    private final UUID id;
    private final String title;
    private final String content;
    private final UUID studentId;
    private final boolean deleted;

    public Post(UUID id, String title, String content, UUID studentId, boolean deleted) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.studentId = studentId;
        this.deleted = deleted;
    }

    public Post markDeleted() {
        return new Post(this.id, this.title, this.content, this.studentId, true);
    }
}

