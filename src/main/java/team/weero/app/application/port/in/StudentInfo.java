package team.weero.app.application.port.in;

import java.util.UUID;
import team.weero.app.domain.student.type.StudentRole;

public record StudentInfo(
    UUID id,
    String name,
    String accountId,
    int grade,
    int classRoom,
    int number,
    StudentRole role) {}
