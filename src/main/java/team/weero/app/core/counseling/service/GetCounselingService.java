package team.weero.app.core.counseling.service;

import team.weero.app.core.counseling.dto.request.CounselingRequest;
import team.weero.app.persistence.student.entity.Student;
import team.weero.app.persistence.teacher.entity.Teacher;

import java.util.UUID;

public interface GetCounselingService {

    Student findByStudent (UUID studentId);

    Teacher findByTeacher (CounselingRequest request);

    boolean existsByTeacherAndDateAndTime (CounselingRequest request);
}
