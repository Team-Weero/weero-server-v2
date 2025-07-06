package team.weero.app.presentation.teacher;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import team.weero.app.core.teacher.dto.request.UpdateNotificationSettingsRequest;
import team.weero.app.core.teacher.dto.response.TeacherResponse;
import team.weero.app.core.teacher.usecase.GetMyTeacherInfoUseCase;
import team.weero.app.core.teacher.usecase.UpdateNotificationSettingsUseCase;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherWebAdapter {

    private final GetMyTeacherInfoUseCase getMyTeacherInfoUseCase;
    private final UpdateNotificationSettingsUseCase updateNotificationSettingsUseCase;

    @GetMapping("/me")
    public TeacherResponse getMyInfo(Authentication authentication) {
        return getMyTeacherInfoUseCase.execute(authentication.getName());
    }

    @PutMapping("/notification-settings")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNotificationSettings(@RequestBody @Valid UpdateNotificationSettingsRequest request,
                                          Authentication authentication) {
        updateNotificationSettingsUseCase.execute(authentication.getName(), request);
    }
}
