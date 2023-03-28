package mitocode.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mitocode.dto.StudentDTO;
import mitocode.exception.ModelNotFoundException;
import mitocode.model.Student;
import mitocode.service.IStudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudentController {

    private final IStudentService service;
    @Qualifier("studentMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> readAll() throws Exception{
        List<StudentDTO> list = service.readAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Student obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentDTO dto) throws Exception{
        Student obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StudentDTO> update(@Valid @RequestBody StudentDTO dto) throws Exception{
        Student obj = service.readById(dto.getIdStudent());

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getIdStudent());
        }

        Student stu = service.update(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(stu), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Student obj = service.readById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<StudentDTO>> findByName(@PathVariable("name") String name){
        List<StudentDTO> lst = service.findByName(name).stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Student>> findPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ){

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Student> pageResponse = service.findPage(pageRequest);

        return new ResponseEntity<>(pageResponse, HttpStatus.OK);
    }

    @GetMapping("/order")
    public ResponseEntity<List<StudentDTO>> findAllOrder(
            @RequestParam(name = "param", defaultValue = "ASC") String param
    ){
        List<StudentDTO> lst = service.findAllOrder(param).stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    //********************************************************************************
    //  Listar estudiantes ordenados de forma descendente por edad usando programación
    //  funcional
    //*****************************************************************************+
    @Operation(summary = "Listar estudiantes ordenados de forma descendente por edad usando programación funcional")
    @GetMapping("/orderbyage")
    public ResponseEntity<List<StudentDTO>> readByAge(){
       List<StudentDTO> lst = service.readByAge().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }
    private StudentDTO convertToDto(Student obj) {
        return mapper.map(obj, StudentDTO.class);
    }

    private Student convertToEntity(StudentDTO dto){
        return mapper.map(dto, Student.class);
    }
}
