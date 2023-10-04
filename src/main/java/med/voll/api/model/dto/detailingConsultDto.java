package med.voll.api.model.dto;

import java.time.LocalDateTime;

public record detailingConsultDto(Long id, Long doctorId, Long patientId, LocalDateTime date){
}
