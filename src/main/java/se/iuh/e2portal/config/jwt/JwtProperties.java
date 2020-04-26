package se.iuh.e2portal.config.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {
	
    private String secretCode = "oldpanther";
    private Long duration = 3600000L; //1h
    
}
