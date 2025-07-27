package team.weero.app.core.nickname.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.weero.app.core.nickname.spi.NicknameGeneratorPort;

@Service
@RequiredArgsConstructor
public class NicknameServiceImpl implements NicknameService{

    private final NicknameGeneratorPort nicknameGeneratorPort;

    @Override
    public String createUniqueNickname() {
        String nickname;
        do {
            nickname = nicknameGeneratorPort.generateNickname();
        } while (nicknameGeneratorPort.existsByNickname(nickname));

        return nickname;
    }
}
