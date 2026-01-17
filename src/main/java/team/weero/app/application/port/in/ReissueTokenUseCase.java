package team.weero.app.application.port.in;

public interface ReissueTokenUseCase {

  TokenResponse execute(String refreshToken);
}
