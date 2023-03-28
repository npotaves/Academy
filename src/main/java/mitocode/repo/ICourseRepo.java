package mitocode.repo;

import mitocode.model.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICourseRepo extends IGenericRepo<Course, Integer>{

//    List<Course> findByName(String name);
    List<Course> findByNameLike(String name);
//    List<Course> findByNameContains(String name);
//    List<Course> findByNameAndStatus(String name, boolean status);
//    List<Course> findByNameOrStatus(String name, boolean status);
//    List<Course> findByStatus(boolean status);
//    List<Course> findByStatusTrue();
//    List<Course> findByStatusFalse();
//    Course findOneByName(String name);
//    List<Course> findTop3ByName(String name);
//    List<Course> findByNameIs(String name);
//    List<Course> findByNameIsNot(String name);
//    List<Course> findByNameIsNull();
//    List<Course> findByNameIsNotNull();
//    List<Course> findByNameEqualsIgnoreCase(String name);
//
//    List<Course> findByIdCourseLessThan(Integer id);
//    List<Course> findByIdCourseLessThanEqual(Integer id);
//    List<Course> findByIdCourseGreaterThan(Integer id);
//    List<Course> findByIdCourseGreaterThanEqual(Integer id);
//    List<Course> findByIdCourseBetween(Integer id1, Integer id2);



}
