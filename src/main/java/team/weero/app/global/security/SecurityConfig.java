package team.weero.app.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import team.weero.app.global.security.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final ObjectMapper objectMapper;

  public SecurityConfig(
      JwtAuthenticationFilter jwtAuthenticationFilter, ObjectMapper objectMapper) {
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    this.objectMapper = objectMapper;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(
            authorize ->
                authorize
                    .requestMatchers(HttpMethod.POST, "/api/auth/signin")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/auth/signup")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/auth/reissue")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/auth/me")
                    .authenticated()
                    .requestMatchers(HttpMethod.POST, "/api/notice/**")
                    .hasAnyAuthority("TEACHER")
                    .requestMatchers(HttpMethod.PUT, "/api/notice/{id}")
                    .hasAnyAuthority("TEACHER")
                    .requestMatchers(HttpMethod.GET, "/api/notices/{id}")
                    .authenticated()
                    .requestMatchers(HttpMethod.GET, "/api/notices")
                    .authenticated()
                    .requestMatchers(HttpMethod.DELETE, "/api/notice/{id}")
                    .hasAnyAuthority("TEACHER")
                    .requestMatchers(HttpMethod.POST, "/api/posts/")
                    .hasAnyAuthority("STUDENT")
                    .requestMatchers(HttpMethod.GET, "/api/posts/")
                    .authenticated()
                    .requestMatchers(HttpMethod.GET, "api/posts/my")
                    .hasAnyAuthority("STUDENT")
                    .requestMatchers(HttpMethod.GET, "api/posts/{postId}")
                    .authenticated()
                    .requestMatchers(HttpMethod.DELETE, "/api/posts/{postId}")
                    .hasAnyAuthority("STUDENT")
                    .requestMatchers(HttpMethod.PATCH, "/api/posts/{postId}")
                    .hasAnyAuthority("STUDENT")
                    .anyRequest()
                    .authenticated())
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling(
            exception ->
                exception
                    .authenticationEntryPoint(customAuthenticationEntryPoint())
                    .accessDeniedHandler(customAccessDeniedHandler()));

    return http.build();
  }

  @Bean
  public AuthenticationEntryPoint customAuthenticationEntryPoint() {
    return (HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException authException) -> {
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

      Map<String, Object> errorResponse =
          Map.of(
              "status", 401,
              "message", "Unauthorized",
              "code", "UNAUTHORIZED");

      try {
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    };
  }

  @Bean
  public AccessDeniedHandler customAccessDeniedHandler() {
    return (HttpServletRequest request,
        HttpServletResponse response,
        org.springframework.security.access.AccessDeniedException accessDeniedException) -> {
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);

      Map<String, Object> errorResponse =
          Map.of(
              "status", 403,
              "message", "Forbidden",
              "code", "FORBIDDEN");

      try {
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    };
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
