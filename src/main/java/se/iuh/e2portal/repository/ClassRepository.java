package se.iuh.e2portal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iuh.e2portal.model.Class;
import se.iuh.e2portal.model.Subject;

import java.util.List;

@Repository
public interface ClassRepository extends CrudRepository<se.iuh.e2portal.model.Class,String> {
    @Query("select c from Class c where c.className like ?1")
    List<Class> findByName(String name);
}
