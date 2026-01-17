package team.weero.app.application.port.in;

public interface UpdateNoticeUseCase {

  NoticeResponse update(UpdateNoticeCommand command);
}
