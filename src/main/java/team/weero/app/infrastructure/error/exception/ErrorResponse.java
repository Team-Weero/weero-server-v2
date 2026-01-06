package team.weero.app.infrastructure.error.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ErrorResponse(HttpStatus status, String message) {}
