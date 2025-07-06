package team.weero.app.persistence.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.weero.app.core.answer.spi.AnswerPort;
import team.weero.app.persistence.answer.entity.Answer;
import team.weero.app.persistence.answer.repository.AnswerRepository;
import team.weero.app.persistence.concern.entity.Concern;
import team.weero.app.persistence.student.entity.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AnswerPersistenceAdapter implements AnswerPort {

    private final AnswerRepository answerRepository;

    public AnswerPersistenceAdapter(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public Optional<Answer> findById(UUID id) {
        return answerRepository.findById(id);
    }

    @Override
    public List<Answer> findByConcern(Concern concern) {
        return answerRepository.findByConcernOrderByCreatedAtAsc(concern);
    }

    @Override
    public List<Answer> findByStudent(Student student) {
        return answerRepository.findByStudentOrderByCreatedAtDesc(student);
    }

    @Override
    public void deleteById(UUID id) {
        answerRepository.deleteById(id);
    }

    @Override
    public int countByConcern(Concern concern) {
        return answerRepository.countByConcern(concern);
    }
}
