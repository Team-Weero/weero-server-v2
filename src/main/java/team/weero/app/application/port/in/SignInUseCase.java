package team.weero.app.application.port.in;

public interface SignInUseCase {

  SignInResponse execute(SignInCommand command);
}
