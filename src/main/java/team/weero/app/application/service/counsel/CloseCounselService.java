package team.weero.app.application.service.counsel;

import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.application.exception.counsel.CounselRequestNotFoundException;
import team.weero.app.application.exception.teacher.TeacherNotFoundException;
import team.weero.app.application.port.in.counsel.CloseCounselUseCase;
import team.weero.app.application.port.in.teacher.dto.response.TeacherInfo;
import team.weero.app.application.port.out.chat.LoadChatRoomPort;
import team.weero.app.application.port.out.chat.NotifyCounselClosedPort;
import team.weero.app.application.port.out.counsel.LoadCounselRequestPort;
import team.weero.app.application.port.out.counsel.SaveCounselRequestPort;
import team.weero.app.application.port.out.teacher.LoadTeacherPort;
import team.weero.app.domain.chat.ChatRoom;
import team.weero.app.domain.counsel.CounselRequest;

@Service
@RequiredArgsConstructor
@Transactional
public class CloseCounselService implements CloseCounselUseCase {

  private final LoadTeacherPort loadTeacherPort;
  private final LoadChatRoomPort loadChatRoomPort;
  private final LoadCounselRequestPort loadCounselRequestPort;
  private final SaveCounselRequestPort saveCounselRequestPort;
  private final NotifyCounselClosedPort notifyCounselClosedPort;

  @Override
  public void execute(UUID chatRoomId, UUID userId) {
    TeacherInfo teacher =
        loadTeacherPort.loadByUserId(userId).orElseThrow(TeacherNotFoundException::new);

    ChatRoom chatRoom =
        loadChatRoomPort.loadById(chatRoomId).orElseThrow(CounselRequestNotFoundException::new);

    if (!chatRoom.getTeacherId().equals(teacher.id())) {
      throw new AccessDeniedException("해당 상담 종료 권한이 없습니다.");
    }

    CounselRequest counselRequest =
        loadCounselRequestPort
            .loadById(chatRoom.getCounselRequestId())
            .orElseThrow(CounselRequestNotFoundException::new);

    saveCounselRequestPort.save(counselRequest.close());

    try {
      notifyCounselClosedPort.notify(chatRoomId);
    } catch (IOException e) {
      throw new RuntimeException("WebSocket 알림 전송 실패", e);
    }
  }
}
