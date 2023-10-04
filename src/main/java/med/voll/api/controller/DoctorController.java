package med.voll.api.controller;

import jakarta.validation.Valid;

import med.voll.api.model.dto.doctor.DoctorDetailingDataDto;
import med.voll.api.model.dto.doctor.DataListingDoctorsDto;
import med.voll.api.model.dto.doctor.DoctorDto;
import med.voll.api.model.dto.doctor.MedicalDataUpdateDto;
import med.voll.api.model.entities.Doctor;
import med.voll.api.model.repositories.DoctorRepository;
import med.voll.api.model.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medico")
@Transactional(rollbackFor = Exception.class)
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorService doctorService;


    @GetMapping("/getById")
    public ResponseEntity<DataListingDoctorsDto> getById(@RequestParam Long id){
        Optional<Doctor> doctorOptional = doctorService.searchById(id);

        Optional<DataListingDoctorsDto> optionalDataListingDoctorsDto = doctorOptional.map(DataListingDoctorsDto::new);

        return optionalDataListingDoctorsDto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getAll")
    public List<DataListingDoctorsDto> getAll(){
        return doctorRepository.findAll().stream().map(DataListingDoctorsDto::new).toList();
    }

    @GetMapping("/getAllPage")
    public ResponseEntity<Page<DataListingDoctorsDto>> getAllOrderBy(@PageableDefault(sort = "recordData", direction = Sort.Direction.DESC) Pageable pageable){
        var page = doctorRepository.findAllByExpireFalse(pageable).map(DataListingDoctorsDto::new);

        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getDetailById(@PathVariable Long id){
            var doctor = this.doctorRepository.getReferenceById(id);
            return ResponseEntity.ok(new DoctorDetailingDataDto(doctor));
    }

    /**
     * Neste método, por boas praticas preciso devolver o código 201,
     * cabeço location com a uri e no corpo da resposta uma representação
     * do recurso recem criado
     *
     * @param doctorDto
     * @param uriComponentsBuilder
     * @return
     */
    @PostMapping
    public ResponseEntity register(@RequestBody @Valid DoctorDto doctorDto, UriComponentsBuilder uriComponentsBuilder) {
        var doctor = new Doctor(doctorDto);

        doctorRepository.save(doctor);

        // Classe que já encapsula o endereço da API, já faz a construção do end uri de maneira automatica.
        var uri = uriComponentsBuilder.path("/medico/{id}").buildAndExpand(doctor.getId()).toUri();


        return ResponseEntity.created(uri).body(new DoctorDetailingDataDto(doctor));
    }

    @PutMapping
    public ResponseEntity update(@RequestBody @Valid MedicalDataUpdateDto medicalDataUpdateDto){
        var doctor = doctorRepository.getReferenceById(medicalDataUpdateDto.id());
        doctor.update(medicalDataUpdateDto);

        return ResponseEntity.ok(new DoctorDetailingDataDto(doctor));
    }

    /**
     * Exclusão Tradicional, nesta exclusão, ela realmente irá apagar do banco de dados.
     */
    @DeleteMapping("deleteTraditional/{id}")
    public ResponseEntity<Void> deleteTraditional(@PathVariable Long id){
        Optional<Doctor> doctorOptional = doctorService.searchById(id);

        if(doctorOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.doctorRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Exclusão Lógica, nesta exclusão, ela irá expirar o registro
     */
    @DeleteMapping("deleteLogic/{id}")
    public ResponseEntity deleteLogic(@PathVariable Long id){
        var doctor = doctorRepository.getReferenceById(id);
        doctor.delete();

        return ResponseEntity.noContent().build();

    }


}
