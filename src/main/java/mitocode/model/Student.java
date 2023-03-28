package mitocode.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity//(name = "XYZ")

public class Student {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStudent;

    @Column(length = 50, nullable = false) //name = "category_name"
    private String name;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column(length = 10, nullable = false)
    private String dni;

    @Column(nullable = false)
    private int age;

}
