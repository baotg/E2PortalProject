package se.iuh.e2portal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.iuh.e2portal.model.MainClass;

import java.util.Optional;

@RepositoryRestResource(itemResourceRel = "main_class", collectionResourceRel = "main_classes", path = "main_class")
public interface MainClassRepository extends PagingAndSortingRepository<MainClass, String> {
    Iterable<MainClass> findByFaculty_FacultyId(String id);

    @Query("select s.mainClass from Student s where s.id = ?#{ principal.id}")
    Optional<MainClass> mine();

}
