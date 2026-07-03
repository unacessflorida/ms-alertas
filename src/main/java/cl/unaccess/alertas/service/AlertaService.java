package cl.unaccess.alertas.service;

import cl.unaccess.alertas.model.Alerta;
import cl.unaccess.alertas.dto.AlertaDTO;
import cl.unaccess.alertas.repository.AlertaRepository;
import cl.unaccess.alertas.client.PacienteClient;
import cl.unaccess.alertas.client.NotificacionClient;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertaService {

    private final AlertaRepository repo;
    private final PacienteClient pacienteClient;
    private final NotificacionClient notificacionClient;

    public AlertaService(AlertaRepository repo, PacienteClient pacienteClient, NotificacionClient notificacionClient) {
        this.repo = repo;
        this.pacienteClient = pacienteClient;
        this.notificacionClient = notificacionClient;
    }

    public Alerta emitirAlerta(AlertaDTO dto) {
        if (!pacienteClient.existePaciente(dto.getPacienteRut())) {
            throw new RuntimeException("El paciente con RUT " + dto.getPacienteRut() + " no existe");
        }

        Alerta a = new Alerta();
        a.setPacienteRut(dto.getPacienteRut());
        a.setDescripcion(dto.getDescripcion());
        a.setNivelGravedad(dto.getNivelGravedad().toUpperCase());
        a.setFechaHora(LocalDateTime.now());
        Alerta guardada = repo.save(a);

        if ("ALTA".equals(guardada.getNivelGravedad()) || "GRAVE".equals(guardada.getNivelGravedad())) {
            notificacionClient.enviarNotificacion(
                guardada.getPacienteRut(),
                "Alerta de gravedad " + guardada.getNivelGravedad() + ": " + guardada.getDescripcion()
            );
        }

        return guardada;
    }

    public List<Alerta> listarPorPaciente(String rut) {
        return repo.findByPacienteRut(rut);
    }
}