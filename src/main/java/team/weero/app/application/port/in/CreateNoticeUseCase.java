package team.weero.app.application.port.in;

public interface CreateNoticeUseCase {

  NoticeResponse create(CreateNoticeCommand command);
}
