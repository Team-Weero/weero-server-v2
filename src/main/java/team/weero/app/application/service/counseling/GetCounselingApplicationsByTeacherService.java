package team.weero.app.application.service.counseling;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.port.in.counseling.GetCounselingApplicationsByTeacherUseCase;
import team.weero.app.application.port.out.counseling.CounselingPort;
import team.weero.app.application.service.counseling.dto.response.CounselingResponse;

@Service
@RequiredArgsConstructor
public class GetCounselingApplicationsByTeacherService
    implements GetCounselingApplicationsByTeacherUseCase {

  private final CounselingPort counselingPort;

  @Transactional(readOnly = true)
  public List<CounselingResponse> execute(UUID teacherId) {
    return counselingPort.findByTeacherId(teacherId).stream()
        .map(CounselingResponse::from)
        .collect(Collectors.toList());
  }
}
