package se.iuh.e2portal.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import se.iuh.e2portal.model.*;

@Configuration
public class RestConfiguration implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Student.class);
        config.exposeIdsFor(MainClass.class);
        config.exposeIdsFor(ModuleClass.class);
        config.exposeIdsFor(Lecturer.class);
        config.exposeIdsFor(TimeTable.class);
        config.exposeIdsFor(Faculty.class);
        config.exposeIdsFor(GradingResult.class);
        config.exposeIdsFor(GradingResultPK.class);
    }
}
