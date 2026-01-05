package team.weero.app.application.service.notice;
import team.weero.app.application.port.in.notice.GetNoticeByIdUseCase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.service.notice.dto.response.NoticeResponse;
import team.weero.app.domain.notice.exception.NoticeNotFoundException;
import team.weero.app.domain.notice.model.Notice;
import team.weero.app.application.port.out.notice.NoticeRepository;
import team.weero.app.domain.teacher.exception.TeacherNotFoundException;
import team.weero.app.domain.teacher.model.Teacher;
import team.weero.app.application.port.out.teacher.TeacherRepository;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class GetNoticeByIdService implements GetNoticeByIdUseCase {

    private final NoticeRepository noticeRepository;
    private final TeacherRepository teacherRepository;

    public GetNoticeByIdService(NoticeRepository noticeRepository, TeacherRepository teacherRepository) {
        this.noticeRepository = noticeRepository;
        this.teacherRepository = teacherRepository;
    }

    public NoticeResponse execute(UUID id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

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
