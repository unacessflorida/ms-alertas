package cl.unaccess.alertas.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class PacienteClient {

    private final WebClient webClient;

    public PacienteClient(@Qualifier("pacientesWebClient") WebClient pacientesWebClient) {
        this.webClient = pacientesWebClient;
    }

    public boolean existePaciente(String rut) {
        try {
            webClient.get()
                .uri("/api/v1/pacientes/rut/{rut}", rut)
                .retrieve()
                .toBodilessEntity()
                .block();
            return true;
        } catch (WebClientResponseException.NotFound e) {
            return false;
        }
    }
}