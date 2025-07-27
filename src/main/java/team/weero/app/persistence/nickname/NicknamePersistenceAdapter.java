package team.weero.app.persistence.nickname;

import lombok.RequiredArgsConstructor;
import team.weero.app.core.nickname.spi.NicknameGeneratorPort;
import team.weero.app.persistence.student.repository.StudentRepository;

@RequiredArgsConstructor
public class NicknamePersistenceAdapter implements NicknameGeneratorPort {
    private final StudentRepository studentRepository;

    @Override
    public boolean existsByNickname(String nickname) {
        return studentRepository.existsByNickname(nickname);
    }
}