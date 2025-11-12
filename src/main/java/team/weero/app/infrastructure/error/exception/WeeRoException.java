package team.weero.app.infrastructure.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WeeRoException extends RuntimeException {

    private final ErrorCode errorCode;

}
