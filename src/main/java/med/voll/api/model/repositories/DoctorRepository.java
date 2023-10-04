package med.voll.api.model.repositories;

import med.voll.api.model.entities.Doctor;
import med.voll.api.model.enums.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findById(Long id);
    Page<Doctor> findAllByExpireFalse(Pageable pageable);

    @Query("""
            select m from Doctor m
            where
            m.active = 1
            and 
            m.specialty = :specalty
            and
            m.id not in(
                select c.doctor.i from Consult c
                where
                c.data = :data
            )
            order by rand()
            limit 1
            """)
    Doctor escolherMedicoAleatorioLivreNaData(Specialty specialty, LocalDateTime date);

}
