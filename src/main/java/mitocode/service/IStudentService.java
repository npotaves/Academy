package mitocode.service;

import mitocode.model.Course;
import mitocode.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentService  extends ICRUD<Student, Integer>{
    List<Student> findByName(String name);
    Page<Student> findPage(Pageable pageable);
    List<Student> findAllOrder(String param);
    List<Student> readByAge();

}
