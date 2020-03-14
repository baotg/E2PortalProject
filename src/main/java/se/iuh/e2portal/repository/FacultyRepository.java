package se.iuh.e2portal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iuh.e2portal.model.Faculty;

import java.util.List;

@Repository
public interface FacultyRepository extends CrudRepository<Faculty, Integer> {

    @Query("select f from Faculty f where f.facultyName = ?1")
    List<Faculty> findByName(String name);
}
