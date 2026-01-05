package team.weero.app.application.service.teacher;
import team.weero.app.application.port.in.teacher.GetMyTeacherInfoUseCase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.service.teacher.dto.response.TeacherResponse;
import team.weero.app.domain.teacher.exception.TeacherNotFoundException;
import team.weero.app.domain.teacher.model.Teacher;
import team.weero.app.application.port.out.teacher.TeacherRepository;

/**
 * 내 선생님 정보 조회 Use Case
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetMyTeacherInfoService implements GetMyTeacherInfoUseCase {

    private final TeacherRepository teacherRepository;

    /**
     * accountId로 내 선생님 정보 조회
     * @param accountId 계정 ID
     * @return TeacherResponse
     */
    public TeacherResponse execute(String accountId) {
        Teacher teacher = teacherRepository.findByAccountId(accountId)
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);

        return TeacherResponse.from(teacher);
    }
}
