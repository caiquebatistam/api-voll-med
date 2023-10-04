package med.voll.api.model.dto.doctor;

import med.voll.api.model.entities.Address;
import med.voll.api.model.entities.Doctor;
import med.voll.api.model.enums.Specialty;

public record DoctorDetailingDataDto(Long id, String name,String email, String crm, String telephone, Specialty specialty, Address address) {

    public DoctorDetailingDataDto(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(),doctor.getTelephone(), doctor.getSpecialty(), doctor.getAddress());
    }
}
