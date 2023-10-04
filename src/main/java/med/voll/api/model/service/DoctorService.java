package med.voll.api.model.service;

import med.voll.api.model.entities.Doctor;
import med.voll.api.model.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public Optional<Doctor> searchById(Long id){
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);

        if(doctorOptional.isPresent()){
            return doctorOptional;
        }
        return Optional.ofNullable(null);
    }

}
