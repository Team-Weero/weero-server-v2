package team.weero.app.core.counseling.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record CounselingRequest(
        UUID studentId,
        UUID teacherId,
        LocalDate date,
        LocalTime time
) {}
