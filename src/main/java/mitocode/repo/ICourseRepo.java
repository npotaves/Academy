package mitocode.repo;

import mitocode.model.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICourseRepo extends IGenericRepo<Course, Integer>{

    List<Course> findByNameLike(String name);
}
