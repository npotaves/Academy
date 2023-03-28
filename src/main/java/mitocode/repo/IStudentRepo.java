package mitocode.repo;

import mitocode.model.Course;
import mitocode.model.Student;

import java.util.List;

public interface IStudentRepo extends IGenericRepo<Student, Integer>{
//    List<Student> findByName(String name);
    List<Student> findByNameLike(String name);
//    List<Student> findByNameContains(String name);

}
