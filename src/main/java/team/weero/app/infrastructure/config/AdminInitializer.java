package team.weero.app.infrastructure.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.persistence.teacher.entity.Teacher;
import team.weero.app.persistence.teacher.repository.TeacherRepository;
import team.weero.app.persistence.user.entity.User;
import team.weero.app.persistence.user.repository.UserRepository;
import team.weero.app.persistence.user.type.UserRole;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminProperties adminProperties;

    @Override
    @Transactional
    public void run(String... args) {
        if (teacherRepository.existsByAccountId(adminProperties.getId())) {
            /**
             * 예외를 던질 시 서버가 시작되지 않을 수 있어서 로그를 찍고 종료함
             */
            log.info("관리자 계정이 이미 존재합니다.");
            return;
        }

        User user = User.builder()
                .password(passwordEncoder.encode(adminProperties.getPassword()))
                .userRole(UserRole.TEACHER)
                .build();

        User savedUser = userRepository.save(user);

        Teacher teacher = Teacher.builder()
                .name(adminProperties.getName())
                .accountId(adminProperties.getId())
                .deviceToken(null)
                .noNotificationStartTime(adminProperties.getNotificationStartTime())
                .noNotificationEndTime(adminProperties.getNotificationEndTime())
                .user(savedUser)
                .build();

        teacherRepository.save(teacher);
    }
}
