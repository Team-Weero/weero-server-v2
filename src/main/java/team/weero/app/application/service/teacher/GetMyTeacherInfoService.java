package team.weero.app.application.service.teacher;
import team.weero.app.application.port.in.teacher.GetMyTeacherInfoUseCase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.service.teacher.dto.response.TeacherResponse;
import team.weero.app.domain.teacher.exception.TeacherNotFoundException;
import team.weero.app.domain.teacher.model.Teacher;
import team.weero.app.application.port.out.teacher.TeacherRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetMyTeacherInfoService implements GetMyTeacherInfoUseCase {

    private final TeacherRepository teacherRepository;

    public TeacherResponse execute(String accountId) {
        Teacher teacher = teacherRepository.findByAccountId(accountId)
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);

        return TeacherResponse.from(teacher);
    }
}
