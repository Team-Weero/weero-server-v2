package team.weero.app.persistence.student;

import lombok.RequiredArgsConstructor;
import team.weero.app.core.nickname.spi.NicknameGeneratorPort;
import team.weero.app.core.student.spi.StudentPort;
import team.weero.app.persistence.student.entity.Student;
import team.weero.app.persistence.student.repository.StudentRepository;

@RequiredArgsConstructor
public class StudentPersistenceAdapter implements NicknameGeneratorPort {

    private final StudentRepository studentRepository;

    @Override
    public boolean existsByNickname(String nickname) {
        return studentRepository.existsByNickname(nickname);
    }
}
