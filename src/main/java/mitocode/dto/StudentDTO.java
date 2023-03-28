package mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {
    private Integer idStudent;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    private String nameStudent;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    private String lastNameStudent;

    @NotNull
    @NotEmpty
    @Size(min = 7, max = 10)
    private String dniStudent;

    @NotNull
    @Min(1)
    @Max(99)
    private int ageStudent;

}
