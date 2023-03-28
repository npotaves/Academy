package mitocode.service.impl;

import lombok.RequiredArgsConstructor;
import mitocode.model.Student;
import mitocode.repo.IGenericRepo;
import mitocode.repo.IStudentRepo;
import mitocode.service.IStudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends CRUDImpl<Student, Integer> implements IStudentService {

    private final IStudentRepo repo;

    @Override
    protected IGenericRepo<Student, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Student> findByName(String name) {
        return repo.findByNameLike("%"+name+"%");
    }

    @Override
    public Page<Student> findPage(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public List<Student> findAllOrder(String param) {
        Sort.Direction direction = param.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        return repo.findAll(Sort.by(direction, "name"));
    }
    @Override
    public List<Student> readByAge() {
        return repo.findAll().stream()
                .sorted(Comparator.comparingInt(Student::getAge).reversed())
                .collect(Collectors.toList());
    }
}
