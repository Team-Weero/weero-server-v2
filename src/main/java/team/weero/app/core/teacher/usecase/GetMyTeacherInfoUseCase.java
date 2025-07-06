package team.weero.app.core.teacher.usecase;

import lombok.RequiredArgsConstructor;
import team.weero.app.core.Usecase;
import team.weero.app.core.teacher.dto.response.TeacherResponse;
import team.weero.app.core.teacher.service.TeacherService;

@Usecase
@RequiredArgsConstructor
public class GetMyTeacherInfoUseCase {

    private final TeacherService teacherService;

    public TeacherResponse execute(String accountId) {
        return teacherService.getMyInfo(accountId);
    }
}
