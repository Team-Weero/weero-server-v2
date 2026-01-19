package team.weero.app.application.port.out.notice;

import team.weero.app.domain.notice.Notice;

public interface SaveNoticePort {

  Notice save(Notice notice);
}
