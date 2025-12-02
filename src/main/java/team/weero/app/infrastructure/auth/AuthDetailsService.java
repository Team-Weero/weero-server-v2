package team.weero.app.infrastructure.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team.weero.app.core.auth.exception.UserNotFoundException;
import team.weero.app.core.auth.spi.CommandAuthPort;
import team.weero.app.persistence.student.type.StudentRole;
import team.weero.app.persistence.user.type.UserRole;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final CommandAuthPort commandAuthPort;

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        return loadUserByUsername(accountId, null, null);
    }

    public UserDetails loadUserByUsername(String accountId, UserRole userRole, StudentRole studentRole) throws UsernameNotFoundException {

        if (userRole != null) {
            if (userRole == UserRole.TEACHER) {
                return commandAuthPort.findTeacherByAccountId(accountId)
                        .map(teacher -> (UserDetails) new AuthDetails(teacher.getUser(), teacher))
                        .orElseThrow(UserNotFoundException::new);
            } else if (userRole == UserRole.STUDENT) {
                return commandAuthPort.findByAccountId(accountId)
                        .map(student -> (UserDetails) new AuthDetails(student.getUser(), student))
                        .orElseThrow(UserNotFoundException::new);
            }
        }

        return commandAuthPort.findTeacherByAccountId(accountId)
                .map(teacher -> (UserDetails) new AuthDetails(teacher.getUser(), teacher))
                .or(() -> commandAuthPort.findByAccountId(accountId)
                        .map(student -> (UserDetails) new AuthDetails(student.getUser(), student)))
                .orElseThrow(UserNotFoundException::new);
    }
}
