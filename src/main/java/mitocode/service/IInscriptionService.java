package mitocode.service;

import mitocode.model.Course;
import mitocode.model.Inscription;
import mitocode.model.Student;

import java.util.List;
import java.util.Map;

public interface IInscriptionService  extends ICRUD<Inscription, Integer>{

    List<Inscription> readAll();

    Map<String, List<Student>> getInscription();

}
