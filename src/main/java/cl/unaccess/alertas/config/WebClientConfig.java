package cl.unaccess.alertas.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${pacientes.service.url}")
    private String pacientesUrl;

    @Value("${notificaciones.service.url}")
    private String notificacionesUrl;

    @Bean
    public WebClient pacientesWebClient() {
        return WebClient.builder().baseUrl(pacientesUrl).build();
    }

    @Bean
    public WebClient notificacionesWebClient() {
        return WebClient.builder().baseUrl(notificacionesUrl).build();
    }
}