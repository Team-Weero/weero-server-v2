package team.weero.app.application.port.in.teacher.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalTime;
import java.util.UUID;

@Schema(description = "선생님 정보")
public record TeacherInfo(
    @Schema(description = "선생님 ID", example = "550e8400-e29b-41d4-a716-446655440000") UUID id,
    @Schema(description = "선생님 이름", example = "김선생") String name,
    @Schema(description = "디바이스 토큰") String deviceToken,
    @Schema(description = "알림 비활성 시작 시간", example = "22:00:00") LocalTime noNotificationStartTime,
    @Schema(description = "알림 비활성 종료 시간", example = "08:00:00") LocalTime noNotificationEndTime) {}
