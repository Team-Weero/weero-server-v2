package team.weero.app.core.notify.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotifyType {
    COUNSELING_APPLIED("상담 신청 알림", "%s 학생이 상담을 신청했어요");

    private final String title;
    private final String body;

    public String createBody(String name) {
        return String.format(body, name);
    }
}
