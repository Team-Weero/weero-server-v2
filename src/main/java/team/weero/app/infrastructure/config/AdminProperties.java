package team.weero.app.infrastructure.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.admin.teacher")
public class AdminProperties {

    private final String id;
    private final String password;
    private final String name;
    private final String notificationStartTime;
    private final String notificationEndTime;
}
