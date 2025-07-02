package team.weero.app.presentation.counseling;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weero.app.core.counseling.dto.request.CounselingRequest;
import team.weero.app.core.counseling.usecase.ApplyForCounselingUseCase;

@RestController
@RequestMapping("/counseling")
@RequiredArgsConstructor
public class CounselingWebAdapter {

    private final ApplyForCounselingUseCase applyForCounselingUseCase;

    @PostMapping("/apply")
    public void applyForCounseling(@RequestBody @Valid CounselingRequest request) {
        applyForCounselingUseCase.execute(request);
    }
}
