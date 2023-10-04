package med.voll.api.model.dto.doctor;

import med.voll.api.model.enums.Specialty;
import med.voll.api.model.entities.Doctor;

public record DataListingDoctorsDto(Long id, String name, String email, String crm, Specialty specialty) {

    public DataListingDoctorsDto(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(),doctor.getSpecialty());
    }

}
