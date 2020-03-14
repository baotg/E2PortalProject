package se.iuh.e2portal.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import se.iuh.e2portal.model.*;
import se.iuh.e2portal.repository.AttendanceRepository;
import se.iuh.e2portal.repository.RoleRepository;
import se.iuh.e2portal.repository.StudentRepository;
import se.iuh.e2portal.service.AnnouncementService;
import se.iuh.e2portal.service.AttendanceService;
import se.iuh.e2portal.service.UserAccountService;

import java.util.HashSet;
import java.util.Set;

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
	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	AttendanceService attendanceRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		Role role_user = new Role(ROLE_USER);
		Role role_admin = new Role(ROLE_ADMIN);
		roleRepository.save(role_user);
		roleRepository.save(role_admin);
		//Initial User & Admin account
		UserAccount user_default = new UserAccount();
		UserAccount admin_default = new UserAccount();
		Person lecturer_user = new Lecturer();
		lecturer_user.setPersonId(Long.parseLong(USER_ID));
		Person lecturer_admin = new Lecturer();
		lecturer_admin.setPersonId(Long.parseLong(ADMIN_ID));
		lecturer_admin.setUserAccount(admin_default);
		admin_default.setPerson(lecturer_admin);
		user_default.setPassword(USER_PWD);
		user_default.setPerson(lecturer_user);
		lecturer_user.setUserAccount(user_default);
		Set<Role> roles_user = new HashSet<Role>();
		roles_user.add(role_user);
		user_default.setRoles(roles_user);
		admin_default.setPassword(ADMIN_PWD);
		Set<Role> roles_admin = new HashSet<Role>();
		roles_admin.add(role_admin);
		roles_admin.add(role_user);

		admin_default.setRoles(roles_admin);
		userAccountService.save(user_default);
		userAccountService.save(admin_default);

	}

}
