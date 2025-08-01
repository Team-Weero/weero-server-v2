package team.weero.app.presentation.notify.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotifyResponse {

    private UUID id;
    private String title;
    private String content;
    private boolean isRead;
    private LocalDateTime createdAt;
}
