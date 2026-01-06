package team.weero.app.application.port.in.counseling;

import java.util.List;
import java.util.UUID;
import team.weero.app.application.service.counseling.dto.response.CounselingResponse;

public interface GetMyCounselingApplicationsUseCase {
  List<CounselingResponse> execute(UUID studentId);
}
