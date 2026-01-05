package team.weero.app.application.service.notice;
import team.weero.app.application.port.in.notice.GetMyNoticesUseCase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.service.notice.dto.response.NoticeResponse;
import team.weero.app.domain.notice.exception.UnauthorizedNoticeAccessException;
import team.weero.app.application.port.out.notice.NoticeRepository;
import team.weero.app.domain.teacher.exception.TeacherNotFoundException;
import team.weero.app.domain.teacher.model.Teacher;
import team.weero.app.application.port.out.teacher.TeacherRepository;
import team.weero.app.adapter.out.persistence.student.repository.StudentJpaRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetMyNoticesService implements GetMyNoticesUseCase {

    private final NoticeRepository noticeRepository;
    private final TeacherRepository teacherRepository;
    private final StudentJpaRepository studentRepository;

    public GetMyNoticesService(NoticeRepository noticeRepository, TeacherRepository teacherRepository, StudentJpaRepository studentRepository) {
        this.noticeRepository = noticeRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    public List<NoticeResponse> execute(String accountId) {
        // Validate teacher access
        if (studentRepository.findByAccountId(accountId).isPresent()) {
            throw UnauthorizedNoticeAccessException.EXCEPTION;
        }

        Teacher teacher = teacherRepository.findByAccountId(accountId)
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);

        return noticeRepository.findByUserId(teacher.getUserId()).stream()
                .map(notice -> new NoticeResponse(
                        notice.getId(),
                        notice.getTitle(),
                        notice.getContents(),
                        teacher.getName()
                ))
                .toList();
    }
}
