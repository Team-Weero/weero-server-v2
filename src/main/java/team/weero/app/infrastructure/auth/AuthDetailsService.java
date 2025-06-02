package team.weero.app.infrastructure.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team.weero.app.core.auth.spi.CommandAuthPort;
import team.weero.app.persistence.student.entity.Student;
import team.weero.app.persistence.user.entity.User;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final CommandAuthPort commandAuthPort;
    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        Student student = commandAuthPort.findByAccountId(accountId)
                .orElseThrow(() -> new UsernameNotFoundException(accountId));

        User user = student.getUser();

        return new AuthDetails(user, student);
    }
}
