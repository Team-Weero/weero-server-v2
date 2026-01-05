package team.weero.app.infrastructure.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import team.weero.app.adapter.out.persistence.teacher.entity.TeacherJpaEntity;
import team.weero.app.adapter.out.persistence.teacher.repository.TeacherJpaRepository;
import team.weero.app.adapter.out.persistence.user.entity.UserJpaEntity;
import team.weero.app.adapter.out.persistence.user.repository.UserJpaRepository;
import team.weero.app.domain.user.model.UserRole;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserJpaRepository userRepository;
    private final TeacherJpaRepository teacherRepository;
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

        UserJpaEntity user = UserJpaEntity.builder()
                .password(passwordEncoder.encode(adminProperties.getPassword()))
                .userRole(UserRole.TEACHER)
                .build();

        UserJpaEntity savedUser = userRepository.save(user);

        TeacherJpaEntity teacher = TeacherJpaEntity.builder()
                .name(adminProperties.getName())
                .accountId(adminProperties.getId())
                .deviceToken("")
                .noNotificationStartTime(adminProperties.getNotificationStartTime())
                .noNotificationEndTime(adminProperties.getNotificationEndTime())
                .user(savedUser)
                .build();

        teacherRepository.save(teacher);
    }
}
