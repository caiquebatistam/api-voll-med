package med.voll.api.model.dto.patient;

import med.voll.api.model.entities.Address;
import med.voll.api.model.entities.Patient;

public record DataDetailingPatientDto(long id, String name, String email, String cpf, String telefone, Address address) {

    public DataDetailingPatientDto(Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf(), patient.getTelephone(), patient.getAddress());
    }

}
