package se.iuh.e2portal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import se.iuh.e2portal.model.Student;
import java.util.List;

@RepositoryRestResource(itemResourceRel = "student", collectionResourceRel = "students", path = "student")
public interface StudentRepository extends CrudRepository<Student,String> {
    @Query("select s from Student s where s.fullName like ?1")
    List<Student> findByName(String name);
//    @Query("select s from Student s where s.classId = ?1")
//    List<Student> findByClass(Class aClass);
}
