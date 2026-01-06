package team.weero.app.adapter.in.web.teacher;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import team.weero.app.application.service.teacher.dto.request.UpdateNotificationSettingsRequest;
import team.weero.app.application.service.teacher.dto.response.TeacherResponse;
import team.weero.app.application.port.in.teacher.GetMyTeacherInfoUseCase;
import team.weero.app.application.port.in.teacher.UpdateNotificationSettingsUseCase;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final GetMyTeacherInfoUseCase getMyTeacherInfoUseCase;
    private final UpdateNotificationSettingsUseCase updateNotificationSettingsUseCase;

    @GetMapping("/me")
    public TeacherResponse getMyInfo(Authentication authentication) {
        return getMyTeacherInfoUseCase.execute(authentication.getName());
    }

    @PutMapping("/notification-settings")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNotificationSettings(
            @RequestBody @Valid UpdateNotificationSettingsRequest request,
            Authentication authentication) {
        updateNotificationSettingsUseCase.execute(authentication.getName(), request);
    }
}
