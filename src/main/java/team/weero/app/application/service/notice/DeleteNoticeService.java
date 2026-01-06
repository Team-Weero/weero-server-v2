package team.weero.app.application.service.notice;

import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.out.persistence.student.repository.StudentJpaRepository;
import team.weero.app.application.port.in.notice.DeleteNoticeUseCase;
import team.weero.app.application.port.out.notice.NoticePort;
import team.weero.app.application.port.out.teacher.TeacherPort;
import team.weero.app.domain.notice.exception.NoticeNotFoundException;
import team.weero.app.domain.notice.exception.UnauthorizedNoticeAccessException;
import team.weero.app.domain.notice.model.Notice;
import team.weero.app.domain.teacher.exception.TeacherNotFoundException;
import team.weero.app.domain.teacher.model.Teacher;

@Service
@Transactional
public class DeleteNoticeService implements DeleteNoticeUseCase {

  private final NoticePort noticePort;
  private final TeacherPort teacherPort;
  private final StudentJpaRepository studentRepository;

  public DeleteNoticeService(
      NoticePort noticePort, TeacherPort teacherPort, StudentJpaRepository studentRepository) {
    this.noticePort = noticePort;
    this.teacherPort = teacherPort;
    this.studentRepository = studentRepository;
  }

  public void execute(UUID id, String accountId) {
    if (studentRepository.findByAccountId(accountId).isPresent()) {
      throw UnauthorizedNoticeAccessException.EXCEPTION;
    }

    Teacher teacher =
        teacherPort
            .findByAccountId(accountId)
            .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);

    Notice notice = noticePort.findById(id).orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

    if (!notice.isOwnedBy(teacher.getUserId())) {
      throw UnauthorizedNoticeAccessException.EXCEPTION;
    }

    noticePort.deleteById(id);
  }
}
