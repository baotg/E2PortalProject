package se.iuh.e2portal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.iuh.e2portal.model.Faculty;

@RepositoryRestResource
public interface FacultyRepository extends CrudRepository<Faculty, String> {
}
