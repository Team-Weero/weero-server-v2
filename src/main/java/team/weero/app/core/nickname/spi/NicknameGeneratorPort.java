package team.weero.app.core.nickname.spi;

public interface NicknameGeneratorPort {

    String generateNickname();

    boolean existsByNickname(String nickname);
}
