package se.iuh.e2portal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iuh.e2portal.model.ModuleClass;

import java.util.List;

@Repository
public interface ModuleClassRepository extends CrudRepository<ModuleClass, String> {
    @Query("select m from ModuleClass m where m.moduleClassName like ?1")
    List<ModuleClass> findByName(String name);
}
