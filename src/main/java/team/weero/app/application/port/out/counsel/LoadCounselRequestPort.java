package team.weero.app.application.port.out.counsel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import team.weero.app.domain.counsel.CounselRequest;

public interface LoadCounselRequestPort {
  Optional<CounselRequest> loadById(UUID id);

  List<CounselRequest> loadAllByStudentId(UUID studentId);

  List<CounselRequest> loadAllByTeacherId(UUID teacherId);

  List<CounselRequest> loadAll();

  List<CounselRequest> loadCompletedBeforeDays(int days);
}
