package team.weero.app.core.notice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.core.notice.dto.request.CreateNoticeRequest;
import team.weero.app.core.notice.dto.request.UpdateNoticeRequest;
import team.weero.app.core.notice.dto.response.NoticeResponse;
import team.weero.app.core.notice.exception.NoticeNotFoundException;
import team.weero.app.core.notice.exception.UnauthorizedNoticeAccessException;
import team.weero.app.core.notice.spi.NoticePort;
import team.weero.app.core.teacher.exception.TeacherNotFoundException;
import team.weero.app.core.teacher.spi.TeacherPort;
import team.weero.app.persistence.notice.entity.Notice;
import team.weero.app.persistence.teacher.entity.Teacher;
import team.weero.app.persistence.student.repository.StudentRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeServiceImpl implements NoticeService {

    private final NoticePort noticePort;
    private final TeacherPort teacherPort;
    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public void createNotice(CreateNoticeRequest request, String accountId) {
        Teacher teacher = validateTeacherAccess(accountId);

        Notice notice = Notice.builder()
                .title(request.title())
                .contents(request.contents())
                .user(teacher.getUser())
                .build();

        noticePort.save(notice);
    }

    @Override
    public NoticeResponse getNoticeById(UUID id) {
        Notice notice = noticePort.findById(id)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        Teacher teacher = getTeacherByUser(notice.getUser());
        
        return new NoticeResponse(
                notice.getId(),
                notice.getTitle(),
                notice.getContents(),
                teacher.getName()
        );
    }

    @Override
    public Page<NoticeResponse> getAllNotices(Pageable pageable) {
        return noticePort.findAll(pageable)
                .map(this::toNoticeResponse);
    }

    @Override
    public List<NoticeResponse> getMyNotices(String accountId) {
        Teacher teacher = validateTeacherAccess(accountId);

        return noticePort.findByUser(teacher.getUser()).stream()
                .map(this::toNoticeResponse)
                .toList();
    }

    @Override
    @Transactional
    public void updateNotice(UUID id, UpdateNoticeRequest request, String accountId) {
        Teacher teacher = validateTeacherAccess(accountId);
        Notice notice = noticePort.findById(id)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        if (!notice.getUser().getId().equals(teacher.getUser().getId())) {
            throw UnauthorizedNoticeAccessException.EXCEPTION;
        }

        notice.update(request.title(), request.contents());
        noticePort.save(notice);
    }

    @Override
    @Transactional
    public void deleteNotice(UUID id, String accountId) {
        Teacher teacher = validateTeacherAccess(accountId);
        Notice notice = noticePort.findById(id)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        if (!notice.getUser().getId().equals(teacher.getUser().getId())) {
            throw UnauthorizedNoticeAccessException.EXCEPTION;
        }

        noticePort.deleteById(id);
    }

    private Teacher validateTeacherAccess(String accountId) {
        // Student인지 먼저 확인
        if (studentRepository.findByAccountId(accountId).isPresent()) {
            throw UnauthorizedNoticeAccessException.EXCEPTION;
        }

        // Teacher 조회
        return teacherPort.findByAccountId(accountId)
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);
    }

    private Teacher getTeacherByUser(team.weero.app.persistence.user.entity.User user) {
        return teacherPort.findByUser(user)
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);
    }

    private NoticeResponse toNoticeResponse(Notice notice) {
        Teacher teacher = getTeacherByUser(notice.getUser());
        return new NoticeResponse(
                notice.getId(),
                notice.getTitle(),
                notice.getContents(),
                teacher.getName()
        );
    }
}
