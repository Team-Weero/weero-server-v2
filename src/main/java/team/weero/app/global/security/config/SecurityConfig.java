package team.weero.app.global.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import team.weero.app.global.error.handler.CustomAccessDeniedHandler;
import team.weero.app.global.error.handler.CustomAuthenticationEntryPoint;
import team.weero.app.global.security.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final CustomAuthenticationEntryPoint authenticationEntryPoint;
  private final CustomAccessDeniedHandler accessDeniedHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .cors(AbstractHttpConfigurer::disable)
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
                    .requestMatchers(
                        "/docs",
                        "/docs/**",
                        "/api-docs",
                        "/api-docs/**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/auth/me")
                    .authenticated()
                    .requestMatchers(HttpMethod.POST, "/api/notices/**")
                    .hasAuthority("TEACHER")
                    .requestMatchers(HttpMethod.PUT, "/api/notices/{id}")
                    .hasAuthority("TEACHER")
                    .requestMatchers(HttpMethod.GET, "/api/notices/{id}")
                    .authenticated()
                    .requestMatchers(HttpMethod.GET, "/api/notices")
                    .authenticated()
                    .requestMatchers(HttpMethod.DELETE, "/api/notices/{id}")
                    .hasAuthority("TEACHER")
                    .requestMatchers(HttpMethod.POST, "/api/posts/")
                    .hasAuthority("STUDENT")
                    .requestMatchers(HttpMethod.GET, "/api/posts/")
                    .authenticated()
                    .requestMatchers(HttpMethod.GET, "/api/posts/my")
                    .hasAuthority("STUDENT")
                    .requestMatchers(HttpMethod.GET, "/api/posts/{postId}")
                    .authenticated()
                    .requestMatchers(HttpMethod.DELETE, "/api/posts/{postId}")
                    .hasAuthority("STUDENT")
                    .requestMatchers(HttpMethod.PATCH, "/api/posts/{postId}")
                    .hasAuthority("STUDENT")
                    .requestMatchers(HttpMethod.POST, "/api/counsel-requests/student")
                    .hasAuthority("STUDENT")
                    .requestMatchers(HttpMethod.PATCH, "/api/counsel-requests/student/{id}")
                    .hasAuthority("STUDENT")
                    .requestMatchers(HttpMethod.DELETE, "/api/counsel-requests/student/{id}")
                    .hasAuthority("STUDENT")
                    .requestMatchers(HttpMethod.GET, "/api/counsel-requests/student/my")
                    .hasAuthority("STUDENT")
                    .requestMatchers(HttpMethod.GET, "/api/counsel-requests/teacher")
                    .hasAuthority("TEACHER")
                    .requestMatchers(HttpMethod.GET, "/api/counsel-requests/teacher/{id}")
                    .hasAuthority("TEACHER")
                    .requestMatchers(HttpMethod.POST, "/api/counsel-requests/teacher/{id}/approve")
                    .hasAuthority("TEACHER")
                    .requestMatchers(HttpMethod.POST, "/api/counsel-requests/teacher/{id}/reject")
                    .hasAuthority("TEACHER")
                    .requestMatchers(HttpMethod.POST, "/api/answers/{postId}")
                    .authenticated()
                    .requestMatchers(HttpMethod.GET, "/api/answers/{postId}")
                    .authenticated()
                    .requestMatchers(HttpMethod.DELETE, "/api/answers/{answerId}")
                    .authenticated()
                    .anyRequest()
                    .authenticated())
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling(
            exception ->
                exception
                    .authenticationEntryPoint(authenticationEntryPoint)
                    .accessDeniedHandler(accessDeniedHandler));

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
