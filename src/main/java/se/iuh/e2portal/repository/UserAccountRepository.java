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
	
	@Query(nativeQuery = true, value = "select * from  user_account a inner join user_account_role b " +
			"on a.account_id = b.account_id inner join role c on c.role_id = b.role_id where c.role_name = :roleName")
	Page<UserAccount> findAllByRoleName(Pageable pageable,@Param("roleName") String roleName);
	
	Page<UserAccount> findAll(Pageable pageable);

	@Query("select a from UserAccount a where a.accountId like %:id%")
	Page<UserAccount> findAll(Pageable pageable,@Param("id") String id);

	@Query(nativeQuery = true, value = "select * from  user_account a inner join user_account_role b on a.account_id = b.account_id inner join role c on c.role_id = b.role_id")
	Page<UserAccount> findAllParent(Pageable pageable,@Param("id") String id);

	@Query(nativeQuery = true, value = "select * from  user_account a inner join user_account_role b on a.account_id = b.account_id inner join role c on c.role_id = b.role_id where c.role_name = :roleName and a.account_id like %:id%")
	Page<UserAccount> findAllByRoleAndId(Pageable pageable,@Param("roleName") String roleId,@Param("id") String id);

}
