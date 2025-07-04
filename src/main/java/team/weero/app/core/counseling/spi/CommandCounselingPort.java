package team.weero.app.core.counseling.spi;

import team.weero.app.persistence.counseling.entity.CounselingApplication;

public interface CommandCounselingPort {
    void save (CounselingApplication application);
}
