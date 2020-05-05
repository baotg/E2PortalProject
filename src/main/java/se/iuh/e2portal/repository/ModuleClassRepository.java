package se.iuh.e2portal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.iuh.e2portal.model.ModuleClass;
import java.util.List;

@RepositoryRestResource(itemResourceRel = "moduleclass", collectionResourceRel = "moduleclass", path = "moduleclass")
public interface ModuleClassRepository extends CrudRepository<ModuleClass, String> {
    @Query("select m from ModuleClass m where m.moduleClassName like ?1")
    List<ModuleClass> findByName(String name);
    Iterable<ModuleClass> findByFaculty_FacultyId(String id);
}
