package team.weero.app.adapter.in.web.counseling;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weero.app.application.service.counseling.dto.request.CounselingRequest;
import team.weero.app.application.service.counseling.dto.response.CounselingResponse;
import team.weero.app.application.port.in.counseling.ApplyForCounselingUseCase;
import team.weero.app.application.port.in.counseling.GetCounselingApplicationsByTeacherUseCase;
import team.weero.app.application.port.in.counseling.GetMyCounselingApplicationsUseCase;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/counseling")
@RequiredArgsConstructor
public class CounselingController {

    private final ApplyForCounselingUseCase applyForCounselingUseCase;
    private final GetMyCounselingApplicationsUseCase getMyCounselingApplicationsUseCase;
    private final GetCounselingApplicationsByTeacherUseCase getCounselingApplicationsByTeacherUseCase;

    @PostMapping("/apply")
    public ResponseEntity<Void> applyForCounseling(@RequestBody @Valid CounselingRequest request) {
        applyForCounselingUseCase.execute(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<CounselingResponse>> getMyCounselingApplications(@PathVariable UUID studentId) {
        List<CounselingResponse> applications = getMyCounselingApplicationsUseCase.execute(studentId);
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<CounselingResponse>> getCounselingApplicationsByTeacher(@PathVariable UUID teacherId) {
        List<CounselingResponse> applications = getCounselingApplicationsByTeacherUseCase.execute(teacherId);
        return ResponseEntity.ok(applications);
    }
}
