package team.weero.app.application.port.out.student;

import team.weero.app.adapter.out.student.entity.StudentJpaEntity;

public interface SaveStudentPort {
  void save(StudentJpaEntity student);
}
