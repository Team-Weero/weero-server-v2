package team.weero.app.infrastructure.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team.weero.app.domain.auth.exception.UserNotFoundException;
import team.weero.app.application.port.out.auth.AuthRepository;
import team.weero.app.domain.student.model.StudentRole;
import team.weero.app.domain.user.model.UserRole;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        return loadUserByUsername(accountId, null, null);
    }

    public UserDetails loadUserByUsername(String accountId, UserRole userRole, StudentRole studentRole) throws UsernameNotFoundException {

        if (userRole != null) {
            if (userRole == UserRole.TEACHER) {
                return authRepository.findTeacherByAccountId(accountId)
                        .map(teacher -> (UserDetails) new AuthDetails(teacher.getUser(), teacher))
                        .orElseThrow(UserNotFoundException::new);
            } else if (userRole == UserRole.STUDENT) {
                return authRepository.findStudentWithUserByAccountId(accountId)
                        .map(student -> (UserDetails) new AuthDetails(student.getUser(), student))
                        .orElseThrow(UserNotFoundException::new);
            }
        }

        return authRepository.findTeacherByAccountId(accountId)
                .map(teacher -> (UserDetails) new AuthDetails(teacher.getUser(), teacher))
                .or(() -> authRepository.findStudentWithUserByAccountId(accountId)
                        .map(student -> (UserDetails) new AuthDetails(student.getUser(), student)))
                .orElseThrow(UserNotFoundException::new);
    }
}
