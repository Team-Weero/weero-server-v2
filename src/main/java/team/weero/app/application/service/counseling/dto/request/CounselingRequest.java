package team.weero.app.application.service.counseling.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import team.weero.app.domain.counseling.model.CounselingLocation;

public record CounselingRequest(
    UUID studentId, UUID teacherId, LocalDate date, LocalTime time, CounselingLocation location) {}
