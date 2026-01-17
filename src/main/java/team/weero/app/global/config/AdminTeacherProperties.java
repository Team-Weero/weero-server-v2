package team.weero.app.global.config;

import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.admin.teacher")
@Getter
@Setter
public class AdminTeacherProperties {

  private String email;
  private String password;
  private String name;
  private String deviceToken;
  private LocalTime notificationStartTime;
  private LocalTime notificationEndTime;
}
