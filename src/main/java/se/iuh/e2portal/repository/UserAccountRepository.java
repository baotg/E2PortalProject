package se.iuh.e2portal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import se.iuh.e2portal.model.UserAccount;

import javax.swing.text.html.Option;
import java.util.Optional;
@RepositoryRestResource(itemResourceRel = "user", collectionResourceRel = "users", path = "users")
public interface UserAccountRepository extends CrudRepository<UserAccount, String> {
}
