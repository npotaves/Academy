package mitocode.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Inscription {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInscription;

    @ManyToOne
    @JoinColumn(name = "id_student", nullable = false, foreignKey = @ForeignKey(name= "FK_Student_Inscription"))
    private Student student;

    @Column(nullable = false)
    private LocalDateTime inscriptionDate;
    @Column(nullable = false)
    private boolean status;

    @OneToMany(mappedBy = "inscription", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<InscriptionDetail> details;
}
