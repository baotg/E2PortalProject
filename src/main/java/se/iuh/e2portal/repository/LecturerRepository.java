package se.iuh.e2portal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.iuh.e2portal.model.Lecturer;

@RepositoryRestResource( itemResourceRel = "lecturer", collectionResourceRel = "lecturers", path = "lecturers")
public interface LecturerRepository extends CrudRepository<Lecturer,String> {

	Page<Lecturer> findAll(Pageable pageable);
    @Query("select a from Lecturer a where a.id like %:id% or a.firstName like %:id% or a.lastName like %:id%")
    Page<Lecturer> findAll(Pageable pageable,@Param("id") String id);
//    @Query("select l from Lecturer l where l.fullName like ?1")
//    List<Lecturer> findByName(String name);
//  //  List<Lecturer> findByFaculty(Faculty faculty);
}
