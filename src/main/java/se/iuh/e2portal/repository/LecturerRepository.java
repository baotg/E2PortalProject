package se.iuh.e2portal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import se.iuh.e2portal.model.Lecturer;

import java.util.List;

@RepositoryRestResource( itemResourceRel = "lecturer", collectionResourceRel = "lecturers", path = "lecturers")
public interface LecturerRepository extends CrudRepository<Lecturer,String> {
    @Query("select l from Lecturer l where l.fullName like ?1")
    List<Lecturer> findByName(String name);
  //  List<Lecturer> findByFaculty(Faculty faculty);
}
