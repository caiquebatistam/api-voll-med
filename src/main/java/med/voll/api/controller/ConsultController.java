package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.model.dto.patient.ConsultationAppointmentDto;
import med.voll.api.model.dto.detailingConsultDto;
import med.voll.api.model.service.ConsultationScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consult")
@Transactional(rollbackFor = Exception.class)
public class ConsultController {


    @Autowired
    private ConsultationScheduleService consultationScheduleService;


    @PostMapping
    public ResponseEntity registerConsult(@RequestBody @Valid ConsultationAppointmentDto consultationAppointmentDto){
        System.out.println(consultationAppointmentDto);

        consultationScheduleService.schedule(consultationAppointmentDto);

        return ResponseEntity.ok(new detailingConsultDto(null, null,null, null));

    }


}
