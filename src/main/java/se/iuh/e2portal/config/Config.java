package se.iuh.e2portal.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.spel.spi.EvaluationContextExtension;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class Config {
    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }
}
