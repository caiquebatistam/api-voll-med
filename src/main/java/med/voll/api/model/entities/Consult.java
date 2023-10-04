package med.voll.api.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "tb_consultas")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    @Column(name = "doutor")
    private Doctor doctor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    @Column(name = "paciente")
    private Patient patient;

    @Column(name = "data")
    private LocalDateTime date;



}
