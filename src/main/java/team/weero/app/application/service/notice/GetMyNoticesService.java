package team.weero.app.application.service.notice;
import team.weero.app.application.port.in.notice.GetMyNoticesUseCase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.service.notice.dto.response.NoticeResponse;
import team.weero.app.domain.notice.exception.UnauthorizedNoticeAccessException;
import team.weero.app.application.port.out.notice.NoticePort;
import team.weero.app.domain.teacher.exception.TeacherNotFoundException;
import team.weero.app.domain.teacher.model.Teacher;
import team.weero.app.application.port.out.teacher.TeacherPort;
import team.weero.app.adapter.out.persistence.student.repository.StudentJpaRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetMyNoticesService implements GetMyNoticesUseCase {

    private final NoticePort noticePort;
    private final TeacherPort teacherPort;
    private final StudentJpaRepository studentRepository;

    public GetMyNoticesService(NoticePort noticePort, TeacherPort teacherPort, StudentJpaRepository studentRepository) {
        this.noticePort = noticePort;
        this.teacherPort = teacherPort;
        this.studentRepository = studentRepository;
    }

    public List<NoticeResponse> execute(String accountId) {
        if (studentRepository.findByAccountId(accountId).isPresent()) {
            throw UnauthorizedNoticeAccessException.EXCEPTION;
        }

        Teacher teacher = teacherPort.findByAccountId(accountId)
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);

        return noticePort.findByUserId(teacher.getUserId()).stream()
                .map(notice -> new NoticeResponse(
                        notice.getId(),
                        notice.getTitle(),
                        notice.getContents(),
                        teacher.getName()
                ))
                .toList();
    }
}
