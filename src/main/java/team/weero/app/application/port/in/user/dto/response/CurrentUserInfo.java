package team.weero.app.application.port.in.user.dto.response;

import java.util.UUID;
import team.weero.app.application.port.in.student.dto.response.StudentInfo;
import team.weero.app.application.port.in.teacher.dto.response.TeacherInfo;
import team.weero.app.domain.auth.type.Authority;

public record CurrentUserInfo(
    UUID id,
    String email,
    Authority authority,
    StudentInfo student,
    TeacherInfo teacher) {}
