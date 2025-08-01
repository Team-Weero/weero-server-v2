package team.weero.app.core.notify.service;

import team.weero.app.persistence.student.entity.Student;
import team.weero.app.persistence.teacher.entity.Teacher;

public interface NotifyService {
    public void notifyCounselingApplied(String studentName, Teacher teacher);
}
