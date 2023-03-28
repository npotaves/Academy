package mitocode.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InscriptionDetail {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInscriptionDetail;

    @ManyToOne
    @JoinColumn(name = "id_course", nullable = false)
    private Course course;

    @Column(length = 10, nullable = false)
    private String classroom;

    @ManyToOne
    @JoinColumn(name = "id_inscription", nullable = false)
    private Inscription inscription;

}
