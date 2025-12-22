package team.weero.app.domain.teacher.repository;

import team.weero.app.domain.teacher.model.Teacher;

import java.util.Optional;
import java.util.UUID;

/**
 * Teacher 도메인 Repository 인터페이스
 * DDD의 Repository 패턴
 */
public interface TeacherRepository {

    /**
     * accountId로 Teacher 조회
     * @param accountId 계정 ID
     * @return Teacher Optional
     */
    Optional<Teacher> findByAccountId(String accountId);

    /**
     * ID로 Teacher 조회
     * @param id Teacher ID
     * @return Teacher Optional
     */
    Optional<Teacher> findById(UUID id);

    /**
     * userId로 Teacher 조회
     * @param userId User ID
     * @return Teacher Optional
     */
    Optional<Teacher> findByUserId(UUID userId);

    /**
     * Teacher 저장
     * @param teacher Teacher 도메인 모델
     * @return 저장된 Teacher
     */
    Teacher save(Teacher teacher);

    /**
     * accountId 존재 여부 확인
     * @param accountId 계정 ID
     * @return 존재 여부
     */
    boolean existsByAccountId(String accountId);
}
