package mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import mitocode.model.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InscriptionReportDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String nameCourse;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIncludeProperties(value = {"nameStudent", "lastNameStudent"})
    private List<StudentDTO> students;

}
