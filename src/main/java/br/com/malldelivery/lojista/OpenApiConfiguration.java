package br.com.malldelivery.lojista;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI apiDocConfiguration() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mall Delivery")
                        .description("API do Mall Delivery")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Rafael Cruz")
                                .email("rafael.cruz@professores.ibmec.edu.br")
                        )
                );
    }
}
