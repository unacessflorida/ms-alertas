package cl.unaccess.alertas.controller;

import cl.unaccess.alertas.model.Alerta;
import cl.unaccess.alertas.dto.AlertaDTO;
import cl.unaccess.alertas.service.AlertaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/v1/alertas")
public class AlertaController {

    private final AlertaService service;

    public AlertaController(AlertaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Alerta> crear(@RequestBody AlertaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.emitirAlerta(dto));
    }

    @GetMapping("/paciente/{rut}")
    public ResponseEntity<List<Alerta>> listarPorPaciente(@PathVariable String rut) {
        return ResponseEntity.ok(service.listarPorPaciente(rut));
    }
}