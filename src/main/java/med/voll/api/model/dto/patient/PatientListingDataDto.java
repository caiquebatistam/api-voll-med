package med.voll.api.model.dto.patient;

import med.voll.api.model.entities.Patient;

public record PatientListingDataDto(Long id, String name, String email, String cpf) {

    public PatientListingDataDto(Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
