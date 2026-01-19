package team.weero.app.application.port.out.post;

import team.weero.app.adapter.out.post.entity.PostJpaEntity;

public interface DeletePostPort {
    void softDelete(PostJpaEntity post);
}
