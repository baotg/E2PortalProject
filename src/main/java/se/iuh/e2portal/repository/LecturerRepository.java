package se.iuh.e2portal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iuh.e2portal.model.Faculty;
import se.iuh.e2portal.model.Lecturer;

import java.util.List;

@Repository
public interface LecturerRepository extends CrudRepository<Lecturer,Long> {
    @Query("select l from Lecturer l where l.fullName like ?1")
    List<Lecturer> findByName(String name);
    List<Lecturer> findByFaculty(Faculty faculty);
}
