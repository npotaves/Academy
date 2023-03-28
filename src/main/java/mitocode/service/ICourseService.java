package mitocode.service;

import mitocode.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICourseService extends ICRUD<Course, Integer>{
    List<Course> findByName(String name);

    Page<Course> findPage(Pageable pageable);

    List<Course> findAllOrder(String param);
}
