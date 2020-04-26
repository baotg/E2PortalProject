package se.iuh.e2portal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.iuh.e2portal.model.UserAccount;

@RepositoryRestResource(itemResourceRel = "user", collectionResourceRel = "users", path = "users")
public interface UserAccountRepository extends CrudRepository<UserAccount, String> {
}
