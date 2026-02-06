package team.weero.app.application.port.out.counsel;

import team.weero.app.domain.counsel.CounselRequest;

public interface SaveCounselRequestPort {
  CounselRequest save(CounselRequest counselRequest);
}
