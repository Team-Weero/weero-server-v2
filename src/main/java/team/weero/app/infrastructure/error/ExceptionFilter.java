package team.weero.app.infrastructure.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;
import team.weero.app.infrastructure.error.exception.ErrorCode;
import team.weero.app.infrastructure.error.exception.ErrorResponse;
import team.weero.app.infrastructure.security.jwt.exception.WeeRoException;

@Slf4j
@RequiredArgsConstructor
public class ExceptionFilter extends OncePerRequestFilter {

  private final ObjectMapper mapper;

  @Override
  protected void doFilterInternal(
      HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException {
    try {
      chain.doFilter(req, res);
    } catch (WeeRoException ex) {
      log.error("Handled WeeRoException: ", ex);
      respondWithError(res, ex.getErrorCode());
    } catch (Exception ex) {
      log.error("Unhandled Exception: ", ex);
      respondWithError(res, ErrorCode.INTERNAL_SERVER_ERROR);
    }
  }

  private void respondWithError(HttpServletResponse response, ErrorCode errorCode)
      throws IOException {
    ErrorResponse body =
        ErrorResponse.builder()
            .status(errorCode.getStatus())
            .message(errorCode.getMessage())
            .build();

    response.setStatus(errorCode.getStatus().value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    mapper.writeValue(response.getWriter(), body);
  }
}
