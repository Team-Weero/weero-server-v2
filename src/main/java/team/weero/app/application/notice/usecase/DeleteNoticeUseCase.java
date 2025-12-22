package team.weero.app.application.notice.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.domain.notice.exception.NoticeNotFoundException;
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
public class DeleteNoticeUseCase {

    private final NoticeRepository noticeRepository;
    private final TeacherRepository teacherRepository;
    private final StudentJpaRepository studentRepository;

    public DeleteNoticeUseCase(NoticeRepository noticeRepository, TeacherRepository teacherRepository, StudentJpaRepository studentRepository) {
        this.noticeRepository = noticeRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    public void execute(UUID id, String accountId) {
        // Validate teacher access
        if (studentRepository.findByAccountId(accountId).isPresent()) {
            throw UnauthorizedNoticeAccessException.EXCEPTION;
        }

        Teacher teacher = teacherRepository.findByAccountId(accountId)
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        if (!notice.isOwnedBy(teacher.getUserId())) {
            throw UnauthorizedNoticeAccessException.EXCEPTION;
        }

        noticeRepository.deleteById(id);
    }
}
