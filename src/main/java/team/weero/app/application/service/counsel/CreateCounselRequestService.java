package team.weero.app.application.service.counsel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.exception.student.StudentNotFoundException;
import team.weero.app.application.exception.teacher.TeacherNotFoundException;
import team.weero.app.application.port.in.counsel.CreateCounselRequestUseCase;
import team.weero.app.application.port.in.counsel.dto.request.CreateCounselRequestCommand;
import team.weero.app.application.port.in.counsel.dto.response.CounselRequestInfo;
import team.weero.app.application.port.in.student.dto.response.StudentInfo;
import team.weero.app.application.port.in.teacher.dto.response.TeacherInfo;
import team.weero.app.application.port.out.counsel.SaveCounselRequestPort;
import team.weero.app.application.port.out.student.LoadStudentPort;
import team.weero.app.application.port.out.teacher.LoadTeacherPort;
import team.weero.app.domain.counsel.CounselRequest;
import team.weero.app.domain.counsel.type.Status;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateCounselRequestService implements CreateCounselRequestUseCase {

  private final SaveCounselRequestPort saveCounselRequestPort;
  private final LoadStudentPort loadStudentPort;
  private final LoadTeacherPort loadTeacherPort;

  @Override
  public CounselRequestInfo execute(CreateCounselRequestCommand command) {

    StudentInfo studentInfo =
        loadStudentPort.loadByUserId(command.userId()).orElseThrow(StudentNotFoundException::new);

    TeacherInfo teacherInfo =
        loadTeacherPort.loadById(command.teacherId()).orElseThrow(TeacherNotFoundException::new);

    CounselRequest counselRequest =
        CounselRequest.builder()
            .status(Status.PENDING)
            .gender(command.gender())
            .hasCounselingExperience(command.hasCounselingExperience())
            .category(command.category())
            .studentId(studentInfo.id())
            .teacherId(command.teacherId())
            .build();

    CounselRequest saved = saveCounselRequestPort.save(counselRequest);

    return CounselRequestInfo.from(saved, studentInfo, teacherInfo);
  }
}
