package team.weero.app.persistence.nickname.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.weero.app.persistence.nickname.entity.Nickname;

import java.util.UUID;

public interface NicknameRepository extends JpaRepository<Nickname, UUID> {
    boolean existsByValue(String value);
}
