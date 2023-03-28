package mitocode.service.impl;

import lombok.RequiredArgsConstructor;
import mitocode.model.Course;
import mitocode.repo.ICourseRepo;
import mitocode.service.ICourseService;
import mitocode.repo.IGenericRepo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends CRUDImpl<Course, Integer> implements ICourseService {
    private final ICourseRepo repo;

    @Override
    protected IGenericRepo<Course, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Course> findByName(String name) {
        return repo.findByNameLike("%"+name+"%");
    }

    @Override
    public Page<Course> findPage(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public List<Course> findAllOrder(String param) {
        Sort.Direction direction = param.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        return repo.findAll(Sort.by(direction, "name"));
    }
}
