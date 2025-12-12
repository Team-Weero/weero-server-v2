package team.weero.app.core.counseling.service;

import team.weero.app.core.counseling.dto.request.CounselingRequest;

import java.util.UUID;

public interface CommandCounselingService {
    void applyForCounseling (CounselingRequest request, UUID studentId);
}
