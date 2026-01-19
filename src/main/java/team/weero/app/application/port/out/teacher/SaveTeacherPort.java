package team.weero.app.application.port.out.teacher;

import team.weero.app.adapter.out.teacher.entity.TeacherJpaEntity;

public interface SaveTeacherPort {
  void save(TeacherJpaEntity teacher);
}
