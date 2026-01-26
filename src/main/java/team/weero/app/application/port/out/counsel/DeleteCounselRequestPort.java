package team.weero.app.application.port.out.counsel;

import java.util.UUID;

public interface DeleteCounselRequestPort {
    void softDelete(UUID id, UUID userId);
}
