package team.weero.app.adapter.out.answer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.weero.app.adapter.out.answer.entity.AnswerJpaEntity;

import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerJpaEntity, UUID> {
}
