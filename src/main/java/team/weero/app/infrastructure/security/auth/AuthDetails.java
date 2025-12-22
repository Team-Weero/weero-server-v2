package team.weero.app.infrastructure.security.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import team.weero.app.infrastructure.persistence.student.entity.StudentJpaEntity;
import team.weero.app.domain.student.model.StudentRole;
import team.weero.app.infrastructure.persistence.teacher.entity.TeacherJpaEntity;
import team.weero.app.infrastructure.persistence.user.entity.UserJpaEntity;
import team.weero.app.domain.user.model.UserRole;

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

    public AuthDetails(UserJpaEntity user, StudentJpaEntity student) {
        this.userId = user.getId();
        this.accountId = student.getAccountId();
        this.name = student.getName();
        this.userRole = UserRole.STUDENT;
        this.studentRole = StudentRole.valueOf(student.getStudentRole().name());
        this.profileId = student.getId();
    }

    public AuthDetails(UserJpaEntity user, TeacherJpaEntity teacher) {
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

        if (studentRole != null) {
            authorities.add(new SimpleGrantedAuthority(studentRole.name()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return "";
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
