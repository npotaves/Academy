package mitocode.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import mitocode.dto.CourseDTO;
import mitocode.exception.ModelNotFoundException;
import mitocode.model.Course;
import mitocode.service.ICourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CourseController {

    private final ICourseService service;
        @Qualifier("courseMapper")
        private final ModelMapper mapper;
        @Operation(summary="Lista todos los cursos.")
        @GetMapping
        public ResponseEntity<List<CourseDTO>> readAll() throws Exception{
            List<CourseDTO> list = service.readAll().stream().map(this::convertToDto).collect(Collectors.toList());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        @Operation(summary="Lista la información del curso pasado como parámetro.")
        @GetMapping("/{id}")
        public ResponseEntity<CourseDTO> readById(@PathVariable("id") Integer id) throws Exception{
            Course obj = service.readById(id);
            if(obj == null){
                throw new ModelNotFoundException("ID NOT FOUND: " + id);
            }
            return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
        }
        @Operation(summary="Inserta un nuevo curso.")
        @PostMapping
        public ResponseEntity<CourseDTO> create(@Valid @RequestBody CourseDTO dto) throws Exception{
            Course obj = service.save(convertToEntity(dto));
            return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
        }
        @Operation(summary = "Actualiza los datos de un curso.")
        @PutMapping
        public ResponseEntity<CourseDTO> update(@Valid @RequestBody CourseDTO dto) throws Exception{
            Course obj = service.readById(dto.getIdCourse());

            if(obj == null){
                throw new ModelNotFoundException("ID NOT FOUND: " + dto.getIdCourse());
            }

            Course cou = service.update(convertToEntity(dto));
            return new ResponseEntity<>(convertToDto(cou), HttpStatus.OK);
        }
        @Operation(summary="Elimina el curso pasado como parámetro.")
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
            Course obj = service.readById(id);

            if(obj == null){
                throw new ModelNotFoundException("ID NOT FOUND: " + id);
            }
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        @Operation(summary="Busca los cursos cuyo nombre coincida con el parámetro ingresado.")
        @GetMapping("/find/name/{name}")
        public ResponseEntity<List<CourseDTO>> findByName(@PathVariable("name") String name){
            List<CourseDTO> lst = service.findByName(name).stream().map(this::convertToDto).collect(Collectors.toList());
            return new ResponseEntity<>(lst, HttpStatus.OK);
        }
        @Operation(summary="Listado de cursos con paginación.")
        @GetMapping("/pagination")
        public ResponseEntity<Page<Course>> findPage(
                @RequestParam(name = "page", defaultValue = "0") int page,
                @RequestParam(name = "size", defaultValue = "10") int size
        ){

            PageRequest pageRequest = PageRequest.of(page, size);
            Page<Course> pageResponse = service.findPage(pageRequest);

            return new ResponseEntity<>(pageResponse, HttpStatus.OK);
        }
        @Operation(summary = "Lista los cursos ordenados por nombre en forma asc o desc según el parámetro.")
        @GetMapping("/order")
        public ResponseEntity<List<CourseDTO>> findAllOrder(
                @RequestParam(name = "param", defaultValue = "ASC") String param
        ){
            List<CourseDTO> lst = service.findAllOrder(param).stream().map(this::convertToDto).collect(Collectors.toList());
            return new ResponseEntity<>(lst, HttpStatus.OK);
        }

        private CourseDTO convertToDto(Course obj) {
            return mapper.map(obj, CourseDTO.class);
        }

        private Course convertToEntity(CourseDTO dto){
            return mapper.map(dto, Course.class);
        }
}
