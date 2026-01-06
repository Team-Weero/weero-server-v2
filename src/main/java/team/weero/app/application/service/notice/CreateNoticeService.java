package team.weero.app.application.service.notice;

import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.out.persistence.student.repository.StudentJpaRepository;
import team.weero.app.application.port.in.notice.CreateNoticeUseCase;
import team.weero.app.application.port.out.notice.NoticePort;
import team.weero.app.application.port.out.teacher.TeacherPort;
import team.weero.app.application.service.notice.dto.request.CreateNoticeRequest;
import team.weero.app.domain.notice.exception.UnauthorizedNoticeAccessException;
import team.weero.app.domain.notice.model.Notice;
import team.weero.app.domain.teacher.exception.TeacherNotFoundException;
import team.weero.app.domain.teacher.model.Teacher;

@Service
@Transactional
public class CreateNoticeService implements CreateNoticeUseCase {

  private final NoticePort noticePort;
  private final TeacherPort teacherPort;
  private final StudentJpaRepository studentRepository;

  public CreateNoticeService(
      NoticePort noticePort, TeacherPort teacherPort, StudentJpaRepository studentRepository) {
    this.noticePort = noticePort;
    this.teacherPort = teacherPort;
    this.studentRepository = studentRepository;
  }

  public void execute(CreateNoticeRequest request, String accountId) {
    if (studentRepository.findByAccountId(accountId).isPresent()) {
      throw UnauthorizedNoticeAccessException.EXCEPTION;
    }

    Teacher teacher =
        teacherPort
            .findByAccountId(accountId)
            .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);

    Notice notice =
        Notice.builder()
            .id(UUID.randomUUID())
            .userId(teacher.getUserId())
            .title(request.title())
            .contents(request.contents())
            .build();

    noticePort.save(notice);
  }
}
