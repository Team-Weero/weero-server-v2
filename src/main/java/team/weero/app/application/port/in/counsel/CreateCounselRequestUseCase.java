package team.weero.app.application.port.in.counsel;

import team.weero.app.application.port.in.counsel.dto.request.CreateCounselRequestCommand;
import team.weero.app.application.port.in.counsel.dto.response.CounselRequestInfo;

public interface CreateCounselRequestUseCase {
  CounselRequestInfo execute(CreateCounselRequestCommand command);
}
