package se.iuh.e2portal.config.jwt;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
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
    }
}
