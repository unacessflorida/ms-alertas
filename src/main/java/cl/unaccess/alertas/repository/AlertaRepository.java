package cl.unaccess.alertas.repository;

import cl.unaccess.alertas.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    List<Alerta> findByPacienteRut(String pacienteRut);
}