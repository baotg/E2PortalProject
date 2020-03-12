package se.iuh.e2portal.component;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import se.iuh.e2portal.model.Role;
import se.iuh.e2portal.model.Student;
import se.iuh.e2portal.model.UserAccount;
import se.iuh.e2portal.repository.RoleRepository;
import se.iuh.e2portal.repository.UserAccountRepository;
import se.iuh.e2portal.service.UserAccountService;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
	private final static String ROLE_ADMIN = "ADMIN";
	private final static String ROLE_USER = "USER";
	private final static String ADMIN_ID = "101010";
	private final static String ADMIN_PWD = "101010";
	private final static String USER_ID = "10101010";
	private final static String USER_PWD = "10101010";
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Role role_user = new Role(ROLE_USER);
		Role role_admin = new Role(ROLE_ADMIN);
		roleRepository.save(role_user);
		roleRepository.save(role_admin);
		//Initial User & Admin account
		UserAccount user_default = new UserAccount();
		UserAccount admin_default = new UserAccount();
		user_default.setAccountId(Long.parseLong(USER_ID));
		user_default.setPassword(USER_PWD);
		Set<Role> roles_user = new HashSet<Role>();
		roles_user.add(role_user);
		user_default.setRoles(roles_user);
		userAccountService.save(user_default);
//		admin_default.setAccountId(Long.parseLong(ADMIN_ID));
//		admin_default.setPassword(ADMIN_PWD);
//		Set<Role> roles_admin = new HashSet<Role>();
//		roles_admin.add(role_admin);
//		roles_admin.add(role_user);
//		admin_default.setRoles(roles_admin);
//		userAccountService.save(admin_default);

	}

}
