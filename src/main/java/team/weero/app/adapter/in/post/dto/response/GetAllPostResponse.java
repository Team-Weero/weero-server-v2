package team.weero.app.adapter.in.post.dto.response;

import java.time.LocalDateTime;

public record GetAllPostResponse(
        /**
         * 좋아요 조회수 추후에 추가
         */
        String title,
        String content,
        String name,
        LocalDateTime createdAt
) {
}
