package team.weero.app.application.service.notice;

import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.port.in.notice.GetNoticeByIdUseCase;
import team.weero.app.application.port.out.notice.NoticePort;
import team.weero.app.application.port.out.teacher.TeacherPort;
import team.weero.app.application.service.notice.dto.response.NoticeResponse;
import team.weero.app.domain.notice.exception.NoticeNotFoundException;
import team.weero.app.domain.notice.model.Notice;
import team.weero.app.domain.teacher.exception.TeacherNotFoundException;
import team.weero.app.domain.teacher.model.Teacher;

@Service
@Transactional(readOnly = true)
public class GetNoticeByIdService implements GetNoticeByIdUseCase {

  private final NoticePort noticePort;
  private final TeacherPort teacherPort;

  public GetNoticeByIdService(NoticePort noticePort, TeacherPort teacherPort) {
    this.noticePort = noticePort;
    this.teacherPort = teacherPort;
  }

  public NoticeResponse execute(UUID id) {
    Notice notice = noticePort.findById(id).orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

    Teacher teacher =
        teacherPort
            .findByUserId(notice.getUserId())
            .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);

    return new NoticeResponse(
        notice.getId(), notice.getTitle(), notice.getContents(), teacher.getName());
  }
}
