package med.voll.api.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.model.dto.patient.PatientRegistrationDataDto;
import med.voll.api.model.dto.patient.PatientUpdateDataDto;

@Table(name = "tb_paciente")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telephone;

    @Column(name = "cpf")
    private String cpf;

    @Embedded
    @Column(name = "Endereco")
    private Address address;

    @Column(name = "ativo")
    private Boolean active;

    public Patient(PatientRegistrationDataDto patientRegistrationData) {
        this.active = true;
        this.name = patientRegistrationData.name();
        this.email = patientRegistrationData.email();
        this.telephone = patientRegistrationData.telephone();
        this.cpf = patientRegistrationData.cpf();
        this.address = new Address(patientRegistrationData.address());
    }

    public void updateInformation(PatientUpdateDataDto patientUpdateDataDto) {
        if (patientUpdateDataDto.name() != null) {
            this.name = patientUpdateDataDto.name();
        }
        if (patientUpdateDataDto.telephone() != null) {
            this.telephone = patientUpdateDataDto.telephone();
        }
        if (patientUpdateDataDto.addressDto() != null) {
            this.address.update(patientUpdateDataDto.addressDto());
        }

    }

    public void delete() {
        this.active = false;
    }


}
