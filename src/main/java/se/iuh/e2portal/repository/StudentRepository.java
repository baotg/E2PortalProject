package se.iuh.e2portal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.RestController;
import se.iuh.e2portal.model.MainClass;
import se.iuh.e2portal.model.Student;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource(itemResourceRel = "student", collectionResourceRel = "students", path = "student")
public interface StudentRepository extends PagingAndSortingRepository<Student,String> {
//    @Query("select s from Student s where s.lastName like ?1")
//    List<Student> findByName(String name);
//    @Query("select s from Student s where s.classId = ?1")
//    List<Student> findByClass(Class aClass);
    @Query("select s from Student s where s.mainClass = ?1")
    List<Student> findByMainClass(MainClass clazz);

    @Query("select  s from  Student s where s.id = ?#{ principal.id}")
    Optional<Student> profile();


}
