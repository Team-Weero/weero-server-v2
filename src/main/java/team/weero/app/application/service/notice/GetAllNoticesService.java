package team.weero.app.application.service.notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.port.in.notice.GetAllNoticesUseCase;
import team.weero.app.application.port.out.notice.NoticePort;
import team.weero.app.application.port.out.teacher.TeacherPort;
import team.weero.app.application.service.notice.dto.response.NoticeResponse;
import team.weero.app.domain.notice.model.Notice;
import team.weero.app.domain.teacher.exception.TeacherNotFoundException;
import team.weero.app.domain.teacher.model.Teacher;

@Service
@Transactional(readOnly = true)
public class GetAllNoticesService implements GetAllNoticesUseCase {

  private final NoticePort noticePort;
  private final TeacherPort teacherPort;

  public GetAllNoticesService(NoticePort noticePort, TeacherPort teacherPort) {
    this.noticePort = noticePort;
    this.teacherPort = teacherPort;
  }

  public Page<NoticeResponse> execute(Pageable pageable) {
    return noticePort.findAll(pageable).map(this::toNoticeResponse);
  }

  private NoticeResponse toNoticeResponse(Notice notice) {
    Teacher teacher =
        teacherPort
            .findByUserId(notice.getUserId())
            .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);
    return new NoticeResponse(
        notice.getId(), notice.getTitle(), notice.getContents(), teacher.getName());
  }
}
