package team.weero.app.adapter.in.web.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalTime;
import java.util.UUID;
import team.weero.app.domain.auth.type.Authority;

@Schema(description = "선생님 사용자 정보 응답")
public record TeacherMeResponse(
    @Schema(description = "사용자 ID") UUID id,
    @Schema(description = "이메일") String email,
    @Schema(description = "권한", example = "TEACHER") Authority authority,
    @Schema(description = "이름", example = "김선생") String name,
    @Schema(description = "디바이스 토큰") String deviceToken,
    @Schema(description = "알림 비활성 시작 시간", example = "22:00:00") LocalTime noNotificationStartTime,
    @Schema(description = "알림 비활성 종료 시간", example = "08:00:00") LocalTime noNotificationEndTime) {}
