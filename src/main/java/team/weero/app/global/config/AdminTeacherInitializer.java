package team.weero.app.global.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.out.teacher.entity.TeacherJpaEntity;
import team.weero.app.adapter.out.teacher.repository.TeacherRepository;
import team.weero.app.adapter.out.user.entity.UserJpaEntity;
import team.weero.app.adapter.out.user.repository.UserRepository;
import team.weero.app.application.port.out.PasswordEncoderPort;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdminTeacherInitializer implements ApplicationRunner {

  private final AdminTeacherProperties adminTeacherProperties;
  private final UserRepository userRepository;
  private final TeacherRepository teacherRepository;
  private final PasswordEncoderPort passwordEncoderPort;

  @Override
  @Transactional
  public void run(ApplicationArguments args) {
    if (userRepository.findByEmail(adminTeacherProperties.getEmail()).isEmpty()) {
      log.info("Creating admin teacher account: {}", adminTeacherProperties.getEmail());

      String encodedPassword = passwordEncoderPort.encode(adminTeacherProperties.getPassword());

      UserJpaEntity user =
          UserJpaEntity.builder()
              .email(adminTeacherProperties.getEmail())
              .password(encodedPassword)
              .build();

      UserJpaEntity savedUser = userRepository.save(user);

      TeacherJpaEntity teacher =
          TeacherJpaEntity.builder()
              .name(adminTeacherProperties.getName())
              .deviceToken(adminTeacherProperties.getDeviceToken())
              .noNotificationStartTime(adminTeacherProperties.getNotificationStartTime())
              .noNotificationEndTime(adminTeacherProperties.getNotificationEndTime())
              .user(savedUser)
              .build();

      teacherRepository.save(teacher);

      log.info("Admin teacher account created successfully");
    } else {
      log.info("Admin teacher account already exists: {}", adminTeacherProperties.getEmail());
    }
  }
}
