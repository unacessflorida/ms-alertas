package cl.unaccess.alertas.controller;

import cl.unaccess.alertas.model.Alerta;
import cl.unaccess.alertas.dto.AlertaDTO;
import cl.unaccess.alertas.service.AlertaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/v1/alertas")
@Tag(name = "Alertas", description = "Gestion de alertas clinicas, notifica si son graves")
public class AlertaController {

    private final AlertaService service;

    public AlertaController(AlertaService service) {
        this.service = service;
    }

    @Operation(summary = "Emitir una alerta clinica (valida paciente, notifica si es grave)")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Alerta emitida",
            content = @Content(examples = @ExampleObject(value = "{\"pacienteRut\":\"12345678-9\",\"descripcion\":\"Presion elevada\",\"nivelGravedad\":\"alta\"}"))),
        @ApiResponse(responseCode = "500", description = "Paciente no existe")
    })
    @PostMapping
    public ResponseEntity<Alerta> crear(@RequestBody AlertaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.emitirAlerta(dto));
    }

    @Operation(summary = "Listar alertas de un paciente por su RUT")
    @ApiResponse(responseCode = "200", description = "Lista de alertas del paciente")
    @GetMapping("/paciente/{rut}")
    public ResponseEntity<List<Alerta>> listarPorPaciente(@PathVariable String rut) {
        return ResponseEntity.ok(service.listarPorPaciente(rut));
    }
}