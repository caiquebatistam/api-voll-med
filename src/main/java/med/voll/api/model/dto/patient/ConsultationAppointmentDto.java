package med.voll.api.model.dto.patient;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.model.enums.Specialty;

import java.time.LocalDateTime;
import java.util.Date;

public record ConsultationAppointmentDto(
        Long doctorId,
        @NotNull
        Long patientId,
        @NotNull
        @Future
        LocalDateTime date,
        Specialty specialty) {
}
