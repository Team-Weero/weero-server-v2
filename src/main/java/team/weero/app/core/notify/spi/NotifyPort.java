package team.weero.app.core.notify.spi;

import team.weero.app.persistence.notify.entity.Notify;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotifyPort {
    Notify save(Notify notify);

    List<Notify> findByTeacherId(UUID teacherId);
}
