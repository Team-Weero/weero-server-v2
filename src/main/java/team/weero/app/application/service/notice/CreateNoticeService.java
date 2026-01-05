package team.weero.app.application.notice.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.notice.dto.request.CreateNoticeRequest;
import team.weero.app.domain.notice.exception.UnauthorizedNoticeAccessException;
import team.weero.app.domain.notice.model.Notice;
import team.weero.app.domain.notice.repository.NoticeRepository;
import team.weero.app.domain.teacher.exception.TeacherNotFoundException;
import team.weero.app.domain.teacher.model.Teacher;
import team.weero.app.domain.teacher.repository.TeacherRepository;
import team.weero.app.infrastructure.persistence.student.repository.StudentJpaRepository;

import java.util.UUID;

@Service
@Transactional
public class CreateNoticeUseCase {

    private final NoticeRepository noticeRepository;
    private final TeacherRepository teacherRepository;
    private final StudentJpaRepository studentRepository;

    public CreateNoticeUseCase(NoticeRepository noticeRepository, TeacherRepository teacherRepository, StudentJpaRepository studentRepository) {
        this.noticeRepository = noticeRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    public void execute(CreateNoticeRequest request, String accountId) {
        // Validate teacher access
        if (studentRepository.findByAccountId(accountId).isPresent()) {
            throw UnauthorizedNoticeAccessException.EXCEPTION;
        }

        Teacher teacher = teacherRepository.findByAccountId(accountId)
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);

        Notice notice = Notice.builder()
                .id(UUID.randomUUID())
                .userId(teacher.getUserId())
                .title(request.title())
                .contents(request.contents())
                .build();

        noticeRepository.save(notice);
    }
}
