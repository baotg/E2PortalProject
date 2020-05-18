package se.iuh.e2portal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import se.iuh.e2portal.model.Faculty;

import java.util.Optional;

@RepositoryRestResource( itemResourceRel = "faculty", collectionResourceRel = "faculties", path = "faculty")
public interface FacultyRepository extends CrudRepository<Faculty, String> {
    @RestResource(path = "mine", rel="mine")
    @Query("select s.mainClass.faculty from Student s where s.id = ?#{principal.id}")
    Optional<Faculty> mine();
}
