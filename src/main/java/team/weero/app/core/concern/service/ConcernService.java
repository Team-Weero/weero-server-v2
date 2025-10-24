package team.weero.app.core.concern.service;

import team.weero.app.core.concern.dto.request.CreateConcernRequest;
import team.weero.app.core.concern.dto.response.ConcernResponse;

import java.util.List;
import java.util.UUID;

public interface ConcernService {
    void createConcern(CreateConcernRequest request, String accountId);
    ConcernResponse getConcernById(UUID id);
    List<ConcernResponse> getMyConcerns(String accountId);
    List<ConcernResponse> getAllConcerns();
    List<ConcernResponse> getUnresolvedConcerns();
    void resolveConcern(UUID id);
    void deleteConcern(UUID id, String accountId);
}
