package se.iuh.e2portal.repository;

import org.springframework.data.repository.CrudRepository;
import se.iuh.e2portal.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRoleName(String name);
}
