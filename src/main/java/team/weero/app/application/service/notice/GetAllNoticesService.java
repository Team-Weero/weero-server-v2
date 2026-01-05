package team.weero.app.application.service.notice;
import team.weero.app.application.port.in.notice.GetAllNoticesUseCase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.service.notice.dto.response.NoticeResponse;
import team.weero.app.domain.notice.model.Notice;
import team.weero.app.application.port.out.notice.NoticeRepository;
import team.weero.app.domain.teacher.exception.TeacherNotFoundException;
import team.weero.app.domain.teacher.model.Teacher;
import team.weero.app.application.port.out.teacher.TeacherRepository;

@Service
@Transactional(readOnly = true)
public class GetAllNoticesService implements GetAllNoticesUseCase {

    private final NoticeRepository noticeRepository;
    private final TeacherRepository teacherRepository;

    public GetAllNoticesService(NoticeRepository noticeRepository, TeacherRepository teacherRepository) {
        this.noticeRepository = noticeRepository;
        this.teacherRepository = teacherRepository;
    }

    public Page<NoticeResponse> execute(Pageable pageable) {
        return noticeRepository.findAll(pageable)
                .map(this::toNoticeResponse);
    }

    private NoticeResponse toNoticeResponse(Notice notice) {
        Teacher teacher = teacherRepository.findByUserId(notice.getUserId())
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);
        return new NoticeResponse(
                notice.getId(),
                notice.getTitle(),
                notice.getContents(),
                teacher.getName()
        );
    }
}
