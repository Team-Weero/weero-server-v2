package team.weero.app.infrastructure.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team.weero.app.core.auth.exception.UserNotFoundException;
import team.weero.app.core.auth.spi.CommandAuthPort;
import team.weero.app.persistence.teacher.repository.TeacherRepository;
import team.weero.app.persistence.user.entity.User;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final CommandAuthPort commandAuthPort;

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {

        return commandAuthPort.findByTeacherAccountId(accountId)
                .map(teacher -> {
            User user = teacher.getUser();
            return (UserDetails) new AuthDetails(user, teacher);
        })
                .or(() -> commandAuthPort.findByStudentAccountId(accountId)
                        .map(student -> {
                            User user = student.getUser();
                            return (UserDetails) new AuthDetails(user, student);
                        }))
                .orElseThrow(UserNotFoundException::new);
    }
}
