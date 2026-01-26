package team.weero.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import team.weero.app.global.security.jwt.JwtProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableConfigurationProperties(JwtProperties.class)
@EnableScheduling
public class WeeRoApplication {

  public static void main(String[] args) {
    SpringApplication.run(WeeRoApplication.class, args);
  }
}
