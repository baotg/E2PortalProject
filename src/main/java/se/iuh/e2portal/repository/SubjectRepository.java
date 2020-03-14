package se.iuh.e2portal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iuh.e2portal.model.Student;
import se.iuh.e2portal.model.Subject;

import java.util.List;

@Repository
public interface SubjectRepository extends CrudRepository<Subject,Long> {
    @Query("select s from Subject s where s.subjectName like ?1")
    List<Subject> findByName(String name);
}
