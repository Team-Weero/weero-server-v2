package team.weero.app.infrastructure.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import team.weero.app.persistence.student.entity.Student;
import team.weero.app.persistence.student.type.StudentRole;
import team.weero.app.persistence.teacher.entity.Teacher;
import team.weero.app.persistence.user.entity.User;
import team.weero.app.persistence.user.type.UserRole;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
public class AuthDetails implements UserDetails {

    private final UUID userId;
    private final String accountId;
    private final String name;
    private final UserRole userRole;
    private final StudentRole studentRole;
    private final UUID profileId;

    public AuthDetails(User user, Student student) {
        this.userId = user.getId();
        this.accountId = student.getAccountId();
        this.name = student.getName();
        this.userRole = UserRole.STUDENT;
        this.studentRole = student.getStudentRole();
        this.profileId = student.getId();
    }

    public AuthDetails(User user, Teacher teacher) {
        this.userId = user.getId();
        this.accountId = teacher.getAccountId();
        this.name = teacher.getName();
        this.userRole = UserRole.TEACHER;
        this.studentRole = null;
        this.profileId = teacher.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.name()));

        if (userRole == UserRole.STUDENT && studentRole == StudentRole.AGENT) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + studentRole.name()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return accountId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
