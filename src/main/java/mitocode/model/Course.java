package mitocode.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Course {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCourse;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String acronym;

    @Column(nullable = false)
    private boolean status;


    public Course(String name, String acronym) {
        this.name = name;
        this.acronym = acronym;
    }

    public Course(String name) {
        this.name = name;
    }
}
