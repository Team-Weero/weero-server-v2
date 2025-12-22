package team.weero.app.presentation.teacher;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import team.weero.app.application.teacher.dto.request.UpdateNotificationSettingsRequest;
import team.weero.app.application.teacher.dto.response.TeacherResponse;
import team.weero.app.application.teacher.usecase.GetMyTeacherInfoUseCase;
import team.weero.app.application.teacher.usecase.UpdateNotificationSettingsUseCase;

/**
 * Teacher 컨트롤러
 * RESTful API 엔드포인트 제공
 */
@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final GetMyTeacherInfoUseCase getMyTeacherInfoUseCase;
    private final UpdateNotificationSettingsUseCase updateNotificationSettingsUseCase;

    /**
     * 내 선생님 정보 조회
     * @param authentication 인증 정보
     * @return TeacherResponse
     */
    @GetMapping("/me")
    public TeacherResponse getMyInfo(Authentication authentication) {
        return getMyTeacherInfoUseCase.execute(authentication.getName());
    }

    /**
     * 알림 설정 업데이트
     * @param request 알림 설정 요청
     * @param authentication 인증 정보
     */
    @PutMapping("/notification-settings")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNotificationSettings(
            @RequestBody @Valid UpdateNotificationSettingsRequest request,
            Authentication authentication) {
        updateNotificationSettingsUseCase.execute(authentication.getName(), request);
    }
}
