package mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InscriptionDTO {

    private Integer idInscription;

    @NotNull
    @JsonIncludeProperties(value = {"idStudent","nameStudent", "lastNameStudent"})
    private StudentDTO student;

    @NotNull
     private LocalDateTime inscriptionDate; //ISO Date

    @NotNull
    private boolean statusInscription;

    @NotNull
    @JsonManagedReference
    private List<InscriptionDetailDTO> details;
}
