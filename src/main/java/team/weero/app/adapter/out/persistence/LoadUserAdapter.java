package team.weero.app.adapter.out.persistence;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.student.repository.StudentRepository;
import team.weero.app.adapter.out.teacher.repository.TeacherRepository;
import team.weero.app.adapter.out.user.repository.UserRepository;
import team.weero.app.application.port.out.LoadUserPort;
import team.weero.app.domain.auth.AuthUser;
import team.weero.app.domain.auth.type.Authority;
import team.weero.app.global.exception.UserNotFoundException;

@Component
@RequiredArgsConstructor
public class LoadUserAdapter implements LoadUserPort {

  private final UserRepository userRepository;
  private final StudentRepository studentRepository;
  private final TeacherRepository teacherRepository;

  @Override
  public Optional<AuthUser> loadByEmail(String email) {
    return userRepository
        .findByEmail(email)
        .map(
            user -> {
              Authority authority = determineAuthority(user.getId());
              return new AuthUser(user.getId(), user.getEmail(), authority);
            });
  }

  @Override
  public String getEncodedPasswordByEmail(String email) {
    return userRepository
        .findByEmail(email)
        .orElseThrow(() -> UserNotFoundException.INSTANCE)
        .getPassword();
  }

  private Authority determineAuthority(java.util.UUID userId) {
    if (studentRepository.findByUser_Id(userId).isPresent()) {
      return Authority.STUDENT;
    }

    if (teacherRepository.findByUser_Id(userId).isPresent()) {
      return Authority.TEACHER;
    }

    throw UserNotFoundException.INSTANCE;
  }
}
