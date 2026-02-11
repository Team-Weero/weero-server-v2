package team.weero.app.application.port.in.counsel.dto.response;

import java.util.List;

public record CounselRequestListInfo(List<CounselRequestInfo> requests) {
  public static CounselRequestListInfo from(List<CounselRequestInfo> infos) {
    return new CounselRequestListInfo(infos);
  }
}
