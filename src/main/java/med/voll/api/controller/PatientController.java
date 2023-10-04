package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.model.dto.doctor.DataListingDoctorsDto;
import med.voll.api.model.dto.patient.DataDetailingPatientDto;
import med.voll.api.model.dto.patient.PatientListingDataDto;
import med.voll.api.model.dto.patient.PatientRegistrationDataDto;
import med.voll.api.model.dto.patient.PatientUpdateDataDto;
import med.voll.api.model.entities.Patient;
import med.voll.api.model.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("patients")
@Transactional(rollbackFor = Exception.class)
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid PatientRegistrationDataDto patientRegistrationDataDto, UriComponentsBuilder uriBuilder) {
        var patient = new Patient(patientRegistrationDataDto);
        patientRepository.save(patient);

        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataDetailingPatientDto(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientListingDataDto>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var page = patientRepository.findAllByActiveTrue(pageable).map(PatientListingDataDto::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid PatientUpdateDataDto patientUpdateDataDto) {
        var patient = patientRepository.getReferenceById(patientUpdateDataDto.id());
        patient.updateInformation(patientUpdateDataDto);

        return ResponseEntity.ok(new DataDetailingPatientDto(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        patient.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        return ResponseEntity.ok(new DataDetailingPatientDto(patient));
    }

}
