package team.weero.app.core.student.spi;

import team.weero.app.persistence.student.entity.Student;

public interface StudentPort {

    public void save(Student student);
}
