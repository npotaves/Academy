package mitocode.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InscriptionDetailDTO {
    @JsonBackReference
    private InscriptionDTO inscription;

    @NotNull
    @JsonIncludeProperties(value = {"idCourse", "nameCourse","acronymCourse"})
    private CourseDTO course;

    @NotNull
    @NotEmpty
    @Size(min = 7, max = 10)
    private String classroom;
}
