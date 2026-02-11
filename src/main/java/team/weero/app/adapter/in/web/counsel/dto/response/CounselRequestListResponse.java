package team.weero.app.adapter.in.web.counsel.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import team.weero.app.application.port.in.counsel.dto.response.CounselRequestListInfo;

@Schema(description = "상담 요청 목록 응답")
public record CounselRequestListResponse(
    @Schema(description = "상담 요청 목록", example = "[]")
        List<CounselRequestResponse> counselRequests) {

  public static CounselRequestListResponse from(CounselRequestListInfo listInfo) {
    List<CounselRequestResponse> responses =
        listInfo.requests().stream().map(CounselRequestResponse::from).toList();
    return new CounselRequestListResponse(responses);
  }
}
