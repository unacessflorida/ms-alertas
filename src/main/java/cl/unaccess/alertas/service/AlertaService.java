package cl.unaccess.alertas.service;

import cl.unaccess.alertas.model.Alerta;
import cl.unaccess.alertas.dto.AlertaDTO;
import cl.unaccess.alertas.repository.AlertaRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertaService {

    private final AlertaRepository repo;

    public AlertaService(AlertaRepository repo) {
        this.repo = repo;
    }

    public Alerta emitirAlerta(AlertaDTO dto) {
        Alerta a = new Alerta();
        a.setPacienteRut(dto.getPacienteRut());
        a.setDescripcion(dto.getDescripcion());
        a.setNivelGravedad(dto.getNivelGravedad().toUpperCase());
        a.setFechaHora(LocalDateTime.now());
        return repo.save(a);
    }

    public List<Alerta> listarPorPaciente(String rut) {
        return repo.findByPacienteRut(rut);
    }
}