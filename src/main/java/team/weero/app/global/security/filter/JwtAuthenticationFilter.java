package team.weero.app.global.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import team.weero.app.application.port.out.JwtPort;
import team.weero.app.application.port.out.LoadUserPort;
import team.weero.app.application.port.out.TokenClaims;
import team.weero.app.domain.auth.AuthUser;
import team.weero.app.global.security.CustomUserDetails;
import team.weero.app.global.security.jwt.JwtProperties;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtPort jwtPort;
  private final LoadUserPort loadUserPort;
  private final JwtProperties jwtProperties;

  public JwtAuthenticationFilter(
      JwtPort jwtPort, LoadUserPort loadUserPort, JwtProperties jwtProperties) {
    this.jwtPort = jwtPort;
    this.loadUserPort = loadUserPort;
    this.jwtProperties = jwtProperties;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    try {
      String token = extractToken(request);

      if (!StringUtils.hasText(token)) {
        filterChain.doFilter(request, response);
        return;
      }

      TokenClaims claims = jwtPort.parseToken(token);

      AuthUser user =
          loadUserPort
              .loadByEmail(claims.email())
              .orElseThrow(() -> new IllegalArgumentException("User not found"));

      CustomUserDetails userDetails =
          new CustomUserDetails(user.getId(), user.getEmail(), user.getAuthority());

      UsernamePasswordAuthenticationToken authentication =
          new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

      SecurityContextHolder.getContext().setAuthentication(authentication);

      filterChain.doFilter(request, response);
    } catch (Exception e) {
      log.warn("JWT authentication failed: {}", e.getMessage());
      filterChain.doFilter(request, response);
    }
  }

  private String extractToken(HttpServletRequest request) {
    String bearerToken = request.getHeader(jwtProperties.getHeader());
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtProperties.getPrefix())) {
      return bearerToken.substring(jwtProperties.getPrefix().length());
    }
    return null;
  }
}
