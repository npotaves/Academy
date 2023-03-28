package mitocode.service.impl;

import lombok.RequiredArgsConstructor;
import mitocode.model.Inscription;
import mitocode.model.InscriptionDetail;
import mitocode.model.Student;
import mitocode.repo.IGenericRepo;
import mitocode.repo.IInscriptionRepo;
import mitocode.service.IInscriptionService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@Service
public class InscriptionServiceImpl extends CRUDImpl<Inscription, Integer> implements IInscriptionService {

    private final IInscriptionRepo repo;
    @Override
    protected IGenericRepo<Inscription, Integer> getRepo() {
        return repo;
    }
    @Override
    public List<Inscription> readAll(){
        return repo.findAll();
    }

    @Override
    public Map<String, List<Student>> getInscription() {
        Stream<List<InscriptionDetail>> stream = repo.findAll().stream().map(Inscription::getDetails);
        Stream<InscriptionDetail> streamDetail = stream.flatMap(Collection::stream);
       return streamDetail
                .collect(groupingBy(d -> d.getCourse().getName(),
                        Collectors.mapping(m -> m.getInscription().getStudent(), Collectors.toList())
                ));
    }
}
