package cl.unaccess.alertas.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class NotificacionClient {

    private final WebClient webClient;

    public NotificacionClient(@Qualifier("notificacionesWebClient") WebClient notificacionesWebClient) {
        this.webClient = notificacionesWebClient;
    }

    public void enviarNotificacion(String pacienteRut, String mensaje) {
        NotificacionRequest body = new NotificacionRequest(pacienteRut, mensaje, "SISTEMA");
        webClient.post()
            .uri("/api/v1/notificaciones")
            .bodyValue(body)
            .retrieve()
            .toBodilessEntity()
            .block();
    }

    public static class NotificacionRequest {
        public String pacienteRut;
        public String mensaje;
        public String tipoCanal;

        public NotificacionRequest(String pacienteRut, String mensaje, String tipoCanal) {
            this.pacienteRut = pacienteRut;
            this.mensaje = mensaje;
            this.tipoCanal = tipoCanal;
        }
    }
}