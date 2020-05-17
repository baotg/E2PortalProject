package se.iuh.e2portal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.iuh.e2portal.model.Person;
@Repository
public interface PersonReposiory extends CrudRepository<Person, String> {
	@Query("select p.lastName, p.firstName from Person p where p.id = :id")
	String getNameById(@Param("id") String id);
}
