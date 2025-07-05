package team.weero.app.core.counseling.service;

import team.weero.app.core.counseling.dto.request.CounselingRequest;

public interface CommandCounselingService {
    void applyForCounseling (CounselingRequest request);
}
