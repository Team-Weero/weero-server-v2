package team.weero.app.application.port.in.post.dto.request;

public record CreatePostCommand(
        String title,
        String content
) {
}
