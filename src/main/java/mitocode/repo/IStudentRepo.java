package mitocode.repo;

import mitocode.model.Course;
import mitocode.model.Student;

import java.util.List;

public interface IStudentRepo extends IGenericRepo<Student, Integer>{
    List<Student> findByNameLike(String name);
}
