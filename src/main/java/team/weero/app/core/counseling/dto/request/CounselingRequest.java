package team.weero.app.core.counseling.dto.request;

import team.weero.app.persistence.counseling.type.CounselingLocation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record CounselingRequest(
        UUID teacherId,
        LocalDate date,
        LocalTime time,
        CounselingLocation location
) {}
