package team.weero.app.application.service.counseling.dto.request;

import team.weero.app.domain.counseling.model.CounselingLocation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record CounselingRequest(
        UUID studentId,
        UUID teacherId,
        LocalDate date,
        LocalTime time,
        CounselingLocation location
) {}
