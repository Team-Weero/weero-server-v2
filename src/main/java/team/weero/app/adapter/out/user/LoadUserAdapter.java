package team.weero.app.adapter.out.user;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.student.repository.StudentRepository;
import team.weero.app.adapter.out.teacher.repository.TeacherRepository;
import team.weero.app.adapter.out.user.entity.UserJpaEntity;
import team.weero.app.adapter.out.user.mapper.UserMapper;
import team.weero.app.adapter.out.user.repository.UserRepository;
import team.weero.app.application.exception.user.UserNotFoundException;
import team.weero.app.application.port.in.user.dto.response.UserInfo;
import team.weero.app.application.port.out.user.LoadUserPort;
import team.weero.app.domain.auth.type.Authority;
import team.weero.app.domain.user.model.User;

@Component
@RequiredArgsConstructor
public class LoadUserAdapter implements LoadUserPort {

  private final UserRepository userRepository;
  private final StudentRepository studentRepository;
  private final TeacherRepository teacherRepository;
  private final UserMapper userMapper;

  @Override
  public Optional<UserInfo> loadByEmail(String email) {
    return userRepository
        .findByEmail(email)
        .map(
            user -> {
              Authority authority = determineAuthority(user.getId());
              return new UserInfo(user.getId(), user.getEmail(), authority);
            });
  }

  @Override
  public String getEncodedPasswordByEmail(String email) {
    return userRepository
        .findByEmail(email)
        .orElseThrow(() -> UserNotFoundException.INSTANCE)
        .getPassword();
  }

  private Authority determineAuthority(UUID userId) {
    if (studentRepository.findByUser_Id(userId).isPresent()) {
      return Authority.STUDENT;
    }

    if (teacherRepository.findByUser_Id(userId).isPresent()) {
      return Authority.TEACHER;
    }

    throw UserNotFoundException.INSTANCE;
  }

  @Override
  public Optional<User> loadById(UUID userId) {
    Optional<UserJpaEntity> entity = userRepository.findById(userId);

    return entity.map(userMapper::toDomain);
  }
}
