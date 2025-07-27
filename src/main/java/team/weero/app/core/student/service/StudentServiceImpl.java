package team.weero.app.core.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.weero.app.core.student.spi.StudentPort;
import team.weero.app.persistence.student.entity.Student;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentPort studentPort;

    @Override
    public void save(Student student) {
        studentPort.save(student);
    }
}
