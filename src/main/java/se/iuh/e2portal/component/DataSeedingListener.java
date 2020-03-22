package se.iuh.e2portal.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import se.iuh.e2portal.model.*;
import se.iuh.e2portal.repository.AttendanceRepository;
import se.iuh.e2portal.repository.LecturerRepository;
import se.iuh.e2portal.repository.RoleRepository;
import se.iuh.e2portal.repository.StudentRepository;
import se.iuh.e2portal.service.AnnouncementService;
import se.iuh.e2portal.service.AttendanceService;
import se.iuh.e2portal.service.ModuleClassService;
import se.iuh.e2portal.service.UserAccountService;

import java.time.LocalDate;
import java.util.*;

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
	private StudentRepository studentRepository;
	@Autowired
	private ModuleClassService moduleClassService;


	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

//		Role role_user = new Role(ROLE_USER);
//		Role role_admin = new Role(ROLE_ADMIN);
//		roleRepository.save(role_user);
//		roleRepository.save(role_admin);
//		//Initial User & Admin account
//		UserAccount user_default = new UserAccount();
//		UserAccount admin_default = new UserAccount();
//		Person lecturer_user = new Lecturer();
//		lecturer_user.setPersonId(Long.parseLong(USER_ID));
//		Person lecturer_admin = new Lecturer();
//		lecturer_admin.setPersonId(Long.parseLong(ADMIN_ID));
//		lecturer_admin.setUserAccount(admin_default);
//		admin_default.setPerson(lecturer_admin);
//		user_default.setPassword(USER_PWD);
//		user_default.setPerson(lecturer_user);
//		lecturer_user.setUserAccount(user_default);
//		Set<Role> roles_user = new HashSet<Role>();
//		roles_user.add(role_user);
//		user_default.setRoles(roles_user);
//		admin_default.setPassword(ADMIN_PWD);
//		Set<Role> roles_admin = new HashSet<Role>();
//		roles_admin.add(role_admin);
//		roles_admin.add(role_user);
//
//		admin_default.setRoles(roles_admin);
//		userAccountService.save(user_default);
//		userAccountService.save(admin_default);
//
//
//		Student student = new Student();
//		student.setFullName("Trần Gia Bảo");
//		student.setHeadClass("DHKTPM12BTT");
//		student.setSpeciality("Kỹ thuật Phần mềm");
//		student.setYear(2016);
//		student.setPhoneNumber("0987654321");
//		student.setAddress("49 Lê Lợi, phường 4, quận Gò Vấp, TP. Hồ Chí Minh");
//		student.setFaculty("Công nghệ Thông tin");
//		student.setDateOfBirth(new GregorianCalendar(1998,Calendar.FEBRUARY,24).getTime());
//		student.setPersonId(16059211l);
//		student.setEmail("tgiabao1340@gmail.com");
//		student.setGender(true);
//		student.setStatus("Đang học");
//
//		UserAccount userAccount = new UserAccount();
//		userAccount.setPerson(student);
//		userAccount.setPassword(USER_PWD);
//		student.setUserAccount(userAccount);
//		userAccount.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findByRoleName("USER"))));
//		userAccountService.save(userAccount);
//
//		initModuleClassAndTimeTable();


	}
	public void initModuleClassAndTimeTable(){
		ModuleClass moduleClass = new ModuleClass();
		Lecturer lecturer = new Lecturer();
		UserAccount userAccount = new UserAccount();
		userAccount.setPassword("123");
		lecturer.setUserAccount(userAccount);
		userAccount.setPerson(lecturer);
		lecturer.setPersonId(1000000l);
		lecturer.setAddress("12 Nguyễn Văn Bảo, phường 4, quận Gò Vấp");
		lecturer.setEmail("cuongt.trinh@iuh.edu.vn");
		lecturer.setFaculty("Ngoại ngữ");
		lecturer.setFullName("Trịnh Thị Cương");
		lecturer.setGender(false);
		lecturer.setPhoneNumber("0987898765");
		lecturer.setStatus("Đang dạy");
		lecturer.setDateOfBirth(new GregorianCalendar(1977,Calendar.FEBRUARY,2).getTime());
		moduleClass.setModuleClassId("422108808");
		moduleClass.setModuleClassName("AV4_ABET_CLC_HK3_2020");
		moduleClass.setLecturer(lecturer);
		moduleClass.setStartDate(new GregorianCalendar(2020,Calendar.APRIL,26).getTime());
		moduleClass.setEndDate(new GregorianCalendar(2020,Calendar.AUGUST,26).getTime());
		moduleClass.setSubjectName("Anh Văn 4");
		moduleClass.setSemester("HK3 2019-2020");
		moduleClass.setNumOfCredit(4);
		Student student = studentRepository.findById(16059211l).get();
		student.setModuleClasses(new ArrayList<>(Arrays.asList(moduleClass)));
		moduleClass.setStudents(new ArrayList<Student>(Arrays.asList(student)));

		TimeTable timeTable1 = new TimeTable();
		timeTable1.setModuleClass(moduleClass);
		timeTable1.setMon("1,3;6,8");
		timeTable1.setWeek(1);

		TimeTable timeTable2 = new TimeTable();
		timeTable2.setModuleClass(moduleClass);
		timeTable2.setMon("1,3;6,8");
		timeTable2.setWeek(2);

		TimeTable timeTable3 = new TimeTable();
		timeTable3.setModuleClass(moduleClass);
		timeTable3.setMon("1,3;6,8");
		timeTable3.setWeek(3);
		moduleClass.setTimeTables(new ArrayList<>(Arrays.asList(timeTable1,timeTable2,timeTable3)));

		userAccountService.save(userAccount);
		moduleClassService.save(moduleClass);
		studentRepository.save(student);






	}

}
