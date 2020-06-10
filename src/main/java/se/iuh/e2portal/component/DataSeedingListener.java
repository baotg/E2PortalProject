package se.iuh.e2portal.component;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import se.iuh.e2portal.config.Message;
import se.iuh.e2portal.model.*;
import se.iuh.e2portal.repository.RoleRepository;
import se.iuh.e2portal.repository.StudentRepository;
import se.iuh.e2portal.service.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
	
	private final static String ADMIN_ID = "admin";
	private final static String ADMIN_PWD = "admin";
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
	@Autowired
	private LecturerService lecturerService;
	@Autowired
	private FacultyService facultyService;
	@Autowired
	private MainClassService mainClassService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private StudentReader studentReader;
	@Autowired
	ResourceLoader resourceLoader;
	@Autowired
	ModuleClassReader moduleClassReader;
	@Autowired
	ExcelFileHandlerService excelFileHandlerService;
	@Value("classpath:static/excels/DANHSACH_SINHVIEN_TEMPLATE.xlsx")
	Resource resourceStudent;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Role role_user = roleService.findByName(Role.USER);
		Role role_student = roleService.findByName(Role.STUDENT);
		Role role_parent = roleService.findByName(Role.PARENT);
		Role role_admin = roleService.findByName(Role.ADMIN);
		if(role_student == null) {
			roleService.save(new Role(Role.STUDENT));
		}
		if(role_parent == null) {
			roleService.save(new Role(Role.PARENT));
		}
		if(role_admin==null){
			roleService.save(new Role(Role.ADMIN));
		}
		if(role_user==null){
			roleService.save(new Role(Role.USER));
		}
		//Initial User & Admin account
		UserAccount admin_default = new UserAccount();
		admin_default.setAccountId(ADMIN_ID);
		admin_default.setPassword(ADMIN_PWD);
		Set<Role> roles_admin = new HashSet<Role>();
		roles_admin.add(roleService.findByName(Role.USER));
		roles_admin.add(roleService.findByName(Role.ADMIN));
		admin_default.setRoles(roles_admin);
		userAccountService.save(admin_default);
		//autoImport();
	}
	public void autoImport(){
		ClassPathResource studentFile = new ClassPathResource("static/excels/DANHSACH_SINHVIEN_TEMPLATE.xlsx");
		ClassPathResource attendacneFile = new ClassPathResource("static/excels/DANHSACH_DIEMDANH_TEMPLATE.xlsx");
		ClassPathResource moduleClassFile = new ClassPathResource("static/excels/DANHSACH_LOP_HOC_PHAN.xlsx");
		ClassPathResource gradingResultFile = new ClassPathResource("static/excels/DANHSACHDIEM_TEMPLATE.xlsx");
		ClassPathResource timetableFile = new ClassPathResource("static/excels/LICHHOC_TEMPLATE.xlsx");
		try {
			importStudent(studentFile);
			//importModuleClass(moduleClassFile);
		}catch (Exception e){
			System.err.println(e);
		}
	}
	// UNDER CONSTRUCTION - CAN'T NOT IMPORT FILEINPUTSTREAM TO POI  :(( NEED HEEELP!!!
	private void importStudent(ClassPathResource studentFile) throws IOException {
		//System.out.println(studentFile.getFilename());
		FileInputStream fileInputStream = new FileInputStream(resourceStudent.getFile());
		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheetAt(0);
		boolean validate = studentReader.validdateFile(sheet);
		MainClass mainClass = null;
		List<Student> students = null;
		if(!validate) {
			System.out.println("STUDENT SEEDING ERROR!");
			return;
		}
		mainClass = students.get(0).getMainClass();
		Faculty faculty = mainClass.getFaculty();
		Lecturer lecturer = mainClass.getLecturer();
		if(!facultyService.findById(faculty.getFacultyId()).isPresent())
			facultyService.save(faculty);
		else
			faculty = facultyService.findById(faculty.getFacultyId()).get();
		mainClass.setFaculty(faculty);
		if(!lecturerService.existsById(lecturer.getId()))
			lecturerService.save(lecturer);
		else
			lecturer = lecturerService.findById(lecturer.getId()).get();
		mainClass.setLecturer(lecturer);
		mainClassService.save(mainClass);
		studentService.saveAll(students);
		excelFileHandlerService.getStudents().clear();
	}
	private void importModuleClass(ClassPathResource moduleClassFile) throws IOException {
		Workbook workbook = new XSSFWorkbook(moduleClassFile.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);
		boolean validate = moduleClassReader.validdateFile(sheet);
		ModuleClass moduleClass = null;
		try {
			moduleClass = moduleClassReader.getModuleClass(sheet);
		} catch (Exception e) {
			System.out.println("MODULECLASS SEEDING ERROR!");
			return;
		}
		Faculty faculty = moduleClass.getFaculty();
		if (moduleClassService.existsById(moduleClass.getModuleClassId())) {
			moduleClass = moduleClassService.findById(moduleClass.getModuleClassId()).get();
		}
		if (facultyService.existsById(faculty.getFacultyId())) {
			faculty = facultyService.findById(faculty.getFacultyId()).get();
		} else
			facultyService.save(faculty);
		for (Student student : moduleClass.getStudents()) {
			if (!studentService.existsById(student.getId())) {
				if (!mainClassService.existsById(student.getMainClass().getClassId()))
					mainClassService.save(student.getMainClass());
				studentService.save(student);
			}
		}
		moduleClassService.save(moduleClass);
	}
}
