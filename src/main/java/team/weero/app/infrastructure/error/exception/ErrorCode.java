package team.weero.app.infrastructure.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User Not Found"),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "User Already Exists"),
    PASSWORD_INCORRECT(HttpStatus.BAD_REQUEST, "Password Incorrect"),

    EXPIRED_JWT(HttpStatus.UNAUTHORIZED, "Expired JWT"),
    INVALID_JWT(HttpStatus.UNAUTHORIZED, "Invalid JWT"),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid Refresh Token"),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "Refresh token Not Found"),

    CONCERN_NOT_FOUND(HttpStatus.NOT_FOUND, "Concern Not Found"),
    ANSWER_NOT_FOUND(HttpStatus.NOT_FOUND, "Answer Not Found"),
    UNAUTHORIZED_ACCESS(HttpStatus.FORBIDDEN, "Unauthorized Access"),
    ALREADY_RESOLVED_CONCERN(HttpStatus.BAD_REQUEST, "Already Resolved Concern"),

    TEACHER_NOT_FOUND(HttpStatus.NOT_FOUND, "Teacher Not Found"),
    NOTICE_NOT_FOUND(HttpStatus.NOT_FOUND, "Notice Not Found"),
    UNAUTHORIZED_NOTICE_ACCESS(HttpStatus.FORBIDDEN, "Unauthorized Notice Access"),

    CHAT_ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "Chat Room Not Found"),
    MESSAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "Message Not Found"),
    UNAUTHORIZED_CHAT_ACCESS(HttpStatus.FORBIDDEN, "Unauthorized Chat Access"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
    DUPLICATE_RESERVATION(HttpStatus.CONFLICT, "Duplicate Reservation");

    private final HttpStatus status;
    private final String message;
}
