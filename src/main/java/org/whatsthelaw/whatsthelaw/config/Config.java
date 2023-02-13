package org.whatsthelaw.whatsthelaw.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableCaching
public class Config {

    @Bean
    public RestTemplate restTemplate(@Value("${app.congress-api.root-uri}") String rootUri,
                                     @Value("${app.congress-api.key}") String apiKey) {
        return new RestTemplateBuilder()
                .basicAuthentication(apiKey, "")
                .rootUri(rootUri)
                .build();
    }

}
