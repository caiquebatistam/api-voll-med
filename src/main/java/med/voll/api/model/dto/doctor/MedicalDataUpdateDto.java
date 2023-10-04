package med.voll.api.model.dto.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.model.dto.AddressDto;

public record MedicalDataUpdateDto(
        @NotNull
        Long id,
        String name,
        String telephone,
        AddressDto addressDto) {
}
