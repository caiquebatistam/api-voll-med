package med.voll.api.model.dto.patient;

import jakarta.validation.constraints.NotNull;
import med.voll.api.model.dto.AddressDto;

public record PatientUpdateDataDto(
        @NotNull
        Long id,
        String name,
        String telephone,
        AddressDto addressDto) {
}
