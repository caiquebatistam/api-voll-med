package med.voll.api.model.repositories;

import med.voll.api.model.entities.Consult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultRepository extends JpaRepository<Consult, Long> {
}
