package team.weero.app.adapter.in.web.counsel.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "상담 요청 목록 응답")
public record CounselRequestListResponse(
    @Schema(description = "상담 요청 목록", example = "[]")
        List<CounselRequestResponse> counselRequests) {}
