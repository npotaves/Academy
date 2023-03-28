package mitocode.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mitocode.dto.InscriptionDTO;
import mitocode.dto.InscriptionReportDTO;
import mitocode.dto.StudentDTO;
import mitocode.exception.ModelNotFoundException;
import mitocode.model.Inscription;
import mitocode.model.InscriptionDetail;
import mitocode.service.IInscriptionService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@RestController
@RequestMapping("/inscriptions")
@RequiredArgsConstructor
public class InscriptionController {

    private final IInscriptionService service;

    @Qualifier("inscriptionMapper")
    private final ModelMapper mapper;
    @Operation(summary="Lista todos las inscripciones.")
    @GetMapping
    public ResponseEntity<List<InscriptionDTO>> readAll() throws Exception {
        List<InscriptionDTO> list = service.readAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @Operation(summary="Lista la información de la inscripción pasada como parámetro.")
    @GetMapping("/{id}")
    public ResponseEntity<InscriptionDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Inscription obj = service.readById(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }
    @Operation(summary="Inserta una nueva inscripción a cursos para un estudiante.")
    @PostMapping
    public ResponseEntity<InscriptionDTO> create(@Valid @RequestBody InscriptionDTO dto) throws Exception {
        Inscription obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }
    @Operation(summary="Modifica una inscripción a cursos.")
    @PutMapping
    public ResponseEntity<InscriptionDTO> update(@Valid @RequestBody InscriptionDTO dto) throws Exception {
        Inscription obj = service.readById(dto.getIdInscription());

        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getIdInscription());
        }

        Inscription sale = service.update(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(sale), HttpStatus.OK);
    }
    @Operation(summary="Elimina una inscripción a cursos.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        Inscription obj = service.readById(id);

        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//********************************************************************************
//    Mostrar la relación de cursos matriculados y sus estudiantes correspondientes
//    usando programación funcional (sugerencia, usar un Map<K,V>)
//    o Ejemplo
//          ▪ Programación
//              • Jaime Medina
//          ▪ Base de Datos
//              • Mito X
//              • Code Y
//********************************************************************************
@Operation(summary = "Mostrar la relación de cursos matriculados y sus estudiantes correspondientes usando programación funcional (sugerencia, usar un Map<K,V>)")
    @GetMapping("/courseinscription")
    public ResponseEntity<List<InscriptionReportDTO>> getCourseInscription() {
        Stream<List<InscriptionDetail>> stream = service.readAll().stream().map(Inscription::getDetails);
        Stream<InscriptionDetail> streamDetail = stream.flatMap(Collection::stream); //list -> list.stream()
        List<InscriptionReportDTO> rdo = streamDetail
                .collect(groupingBy(d -> d.getCourse().getName(),
                        Collectors.mapping(m ->
                                        m.getInscription().getStudent()
                                , Collectors.toList())
                ))
                .entrySet()
                .stream()
                .map(entry ->
                        new InscriptionReportDTO(entry.getKey(), mapper.map(entry.getValue(), new TypeToken<List<StudentDTO>>() {
                        }.getType())
                        ))
                .collect(toList());
        return new ResponseEntity<>(rdo, HttpStatus.OK);
    }
    private InscriptionDTO convertToDto(Inscription obj) {
        return mapper.map(obj, InscriptionDTO.class);
    }

    private Inscription convertToEntity(InscriptionDTO dto) {
        return mapper.map(dto, Inscription.class);
    }
}
