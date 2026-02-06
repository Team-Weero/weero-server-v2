package team.weero.app.adapter.out.counsel.mapper;

import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.counsel.entity.CounselRequestJpaEntity;
import team.weero.app.adapter.out.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.teacher.entity.TeacherJpaEntity;
import team.weero.app.domain.counsel.CounselRequest;

@Component
public class CounselRequestMapper {

  public CounselRequest toDomain(CounselRequestJpaEntity entity) {
    return CounselRequest.builder()
        .id(entity.getId())
        .status(entity.getStatus())
        .gender(entity.getGender())
        .hasCounselingExperience(entity.isHasCounselingExperience())
        .category(entity.getCategory())
        .studentId(entity.getStudent().getId())
        .studentName(entity.getStudent().getName())
        .teacherId(entity.getTeacher().getId())
        .teacherName(entity.getTeacher().getName())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .deletedTime(entity.getDeletedTime())
        .build();
  }

  public static CounselRequestJpaEntity toEntity(
      CounselRequest counselRequest, StudentJpaEntity student, TeacherJpaEntity teacher) {
    return CounselRequestJpaEntity.builder()
        .status(counselRequest.getStatus())
        .gender(counselRequest.getGender())
        .hasCounselingExperience(counselRequest.isHasCounselingExperience())
        .category(counselRequest.getCategory())
        .student(student)
        .teacher(teacher)
        .build();
  }
}
