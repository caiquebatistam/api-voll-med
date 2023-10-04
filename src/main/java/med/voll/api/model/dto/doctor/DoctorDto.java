package med.voll.api.model.dto.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import med.voll.api.model.dto.AddressDto;
import med.voll.api.model.enums.Specialty;

import java.time.LocalDateTime;

public record DoctorDto(
        @NotNull
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telephone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Specialty specialty,
        LocalDateTime recordDate,
        @NotNull
        @Valid
        AddressDto addressDto) {
}
