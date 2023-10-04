package med.voll.api.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import med.voll.api.model.dto.doctor.DoctorDto;
import med.voll.api.model.dto.doctor.MedicalDataUpdateDto;
import med.voll.api.model.enums.Specialty;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "tb_medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "telefone")
    private String telephone;
    @Column(name = "crm")
    private String crm;
    @CreationTimestamp
    @Column(name = "registro_data")
    private LocalDateTime recordData;
    @Enumerated(EnumType.STRING)
    @Column(name = "especialidade")
    private Specialty specialty;
    @Column(name = "endereco")
    @Embedded
    private Address address;
    @Column(name = "expirado")
    @NotNull
    private Boolean expire;

    public Doctor(DoctorDto doctorDto){
        this.expire = false;
        this.name = doctorDto.name();
        this.email = doctorDto.email();
        this.telephone = doctorDto.telephone();
        this.crm = doctorDto.crm();
        this.specialty = doctorDto.specialty();
        this.address = new Address(doctorDto.addressDto());
    }

    public void update(MedicalDataUpdateDto medicalDataUpdateDto){
        if(medicalDataUpdateDto.name() != null){
            this.name = medicalDataUpdateDto.name();
        }
        if(medicalDataUpdateDto.telephone() != null) {
            this.telephone = medicalDataUpdateDto.telephone();
        }
        if(medicalDataUpdateDto.addressDto() != null){
            this.address.update(medicalDataUpdateDto.addressDto());
        }
    }

    public void delete(){
        this.expire = true;
    }
}
