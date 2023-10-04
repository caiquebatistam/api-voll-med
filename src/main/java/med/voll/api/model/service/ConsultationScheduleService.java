package med.voll.api.model.service;

import med.voll.api.model.dto.patient.ConsultationAppointmentDto;
import med.voll.api.model.entities.Consult;
import med.voll.api.model.entities.Doctor;
import med.voll.api.model.enums.Specialty;
import med.voll.api.model.exception.ValidationException;
import med.voll.api.model.repositories.ConsultRepository;
import med.voll.api.model.repositories.DoctorRepository;
import med.voll.api.model.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultationScheduleService {

    @Autowired
    private ConsultRepository consultRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public void schedule(ConsultationAppointmentDto consultationAppointmentDto){

        if(!patientRepository.existsById(consultationAppointmentDto.patientId())){
            throw new ValidationException("Id do paciente informado não existe");
        }
        if(consultationAppointmentDto.doctorId() != null && !doctorRepository.existsById(consultationAppointmentDto.doctorId())){
            throw new ValidationException("Id do doutor informado não existe");
        }

        var patient = patientRepository.findById(consultationAppointmentDto.patientId()).get();
        var doctor = doctorRepository.findById(consultationAppointmentDto.doctorId()).get();
        var consult = new Consult(null,doctor,patient,consultationAppointmentDto.date());

        this.consultRepository.save(consult);
    }


    public Doctor toChooseDoctor(ConsultationAppointmentDto consultationAppointmentDto){
        if(consultationAppointmentDto.doctorId() != null){
            return doctorRepository.getReferenceById(consultationAppointmentDto.doctorId());
        }
        if(consultationAppointmentDto.specialty() == null){
            throw new ValidationException("Especialidade é obrigatória quando o médico não for escolhido");
        }
        return doctorRepository.escolherMedicoAleatorioLivreNaData(Specialty.CARDIOLOGIA, consultationAppointmentDto.date());
    }

}
