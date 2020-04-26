package se.iuh.e2portal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iuh.e2portal.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRoleName(String name);

}
