package team.weero.app.adapter.out.student.mapper;

import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.student.entity.StudentJpaEntity;
import team.weero.app.adapter.out.user.entity.UserJpaEntity;
import team.weero.app.domain.student.domain.Student;

@Component
public class StudentMapper {

  public Student toDomain(StudentJpaEntity entity) {
    return Student.builder()
        .id(entity.getId())
        .accountId(entity.getAccountId())
        .name(entity.getName())
        .nickname(entity.getNickname())
        .grade(entity.getGrade())
        .classRoom(entity.getClassRoom())
        .number(entity.getNumber())
        .role(entity.getRole())
        .userId(entity.getUser().getId())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .build();
  }

  public static StudentJpaEntity toEntity(Student student, UserJpaEntity user) {
    return StudentJpaEntity.builder()
        .accountId(student.getAccountId())
        .name(student.getName())
        .nickname(student.getNickname())
        .grade(student.getGrade())
        .classRoom(student.getClassRoom())
        .number(student.getNumber())
        .role(student.getRole())
        .user(user)
        .build();
  }
}
