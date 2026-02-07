package team.weero.app.application.port.out.counsel;

import java.util.UUID;

public interface CheckCounselRequestOwnerPort {
  boolean isStudentOwner(UUID counselRequestId, UUID studentId);

  boolean isTeacherOwner(UUID counselRequestId, UUID teacherId);
}
