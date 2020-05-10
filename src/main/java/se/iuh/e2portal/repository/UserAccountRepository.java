package se.iuh.e2portal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.iuh.e2portal.model.UserAccount;

@RepositoryRestResource(itemResourceRel = "user", collectionResourceRel = "users", path = "users")
public interface UserAccountRepository extends CrudRepository<UserAccount, String> {

	Page<UserAccount> findAll(Pageable pageable);
	@Query("select a from UserAccount a where a.accountId like %:id%")
	Page<UserAccount> findAll(Pageable pageable,@Param("id") String id);
}
