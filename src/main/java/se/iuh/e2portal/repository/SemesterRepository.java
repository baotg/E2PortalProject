package se.iuh.e2portal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iuh.e2portal.model.Semester;

@Repository
public interface SemesterRepository extends CrudRepository<Semester, Integer> {
}
