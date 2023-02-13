package org.whatsthelaw.whatsthelaw.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("app")
public class AppProperties {

    public record CongressApiProperties(String rootUri, String key) {}

    private CongressApiProperties congressApi;

}
