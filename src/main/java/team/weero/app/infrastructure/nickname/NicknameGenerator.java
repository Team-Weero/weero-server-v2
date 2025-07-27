package team.weero.app.infrastructure.nickname;

import org.springframework.stereotype.Component;
import team.weero.app.core.nickname.spi.NicknameGeneratorPort;
import team.weero.app.core.student.spi.StudentPort;

import java.util.List;
import java.util.Random;

@Component
public class NicknameGenerator implements NicknameGeneratorPort {
    private final List<String> adjectives = List.of("귀여운", "멋진", "빠른", "행복한", "용감한", "고민하는", "집중하는", "예쁜");
    private final List<String> nouns = List.of("토끼", "호랑이", "사자", "독수리", "여우", "강아지", "개구리", "고양이", "펭귄", "오리", "병아리", "소라게");
    private final Random random = new Random();

    public String generateNickname() {
        String adjective = adjectives.get(random.nextInt(adjectives.size()));
        String noun = nouns.get(random.nextInt(nouns.size()));
        int number = random.nextInt(1000);

        return adjective + noun + number;
    }
}
