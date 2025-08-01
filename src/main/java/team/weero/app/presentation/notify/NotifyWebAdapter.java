package team.weero.app.presentation.notify;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.weero.app.persistence.notify.NotifyPersistenceAdapter;
import team.weero.app.persistence.notify.entity.Notify;
import team.weero.app.persistence.notify.mapper.NotifyMapper;
import team.weero.app.presentation.notify.dto.NotifyResponse;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notify")
@RequiredArgsConstructor
public class NotifyWebAdapter {

    private final NotifyPersistenceAdapter notifyPersistenceAdapter;
    private final NotifyMapper notifyMapper;

    @GetMapping("/notifications")
    public List<NotifyResponse> getNotifications(@RequestParam UUID teacherId) {
        List<Notify> entities =notifyPersistenceAdapter.findByTeacherId(teacherId);
        return notifyMapper.toDTOList(entities);
    }
}
