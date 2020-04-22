package se.iuh.e2portal.controller;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.iuh.e2portal.component.StudentReader;
import se.iuh.e2portal.model.Faculty;
import se.iuh.e2portal.model.Lecturer;
import se.iuh.e2portal.model.MainClass;
import se.iuh.e2portal.model.Student;
import se.iuh.e2portal.service.ExcelFileHandlerService;
import se.iuh.e2portal.service.FacultyService;
import se.iuh.e2portal.service.LecturerService;
import se.iuh.e2portal.service.MainClassService;
import se.iuh.e2portal.service.StudentService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private MainClassService mainClassService;
	@Autowired
	private FacultyService facultyService;
	@Autowired
	private StudentReader studentReader;
	@Autowired
	private LecturerService lecturerService;
	@Autowired
	private ExcelFileHandlerService excelFileHandlerService;
	//    @GetMapping("")
	//    public String getMainClass(@PageableDefault(size = 10) Pageable pageable, Model model) {
	//        Iterable<Student> page = studentService.findAll();
	//        model.addAttribute("page", page);
	//        return "student";
	//    }
	@GetMapping("")
	public String getMainClass(Model model){
		model.addAttribute("studentList",studentService.findAll());
		return "studentdetail::student";
	}
	@GetMapping("/{id}")
	public String getStudent(@PathVariable("id") String id, Model model){
		Optional<Student> student = studentService.findById(id);
		if(!student.isPresent())
			return "redirect:/";
		model.addAttribute("student", student.get());
		return "view-student";
	}
	@GetMapping("/edit/")
	public String editStudent(@PathVariable("id") String id, Model model){
		Optional<Student> result = studentService.findById(id);
		if(result.isPresent()){
			model.addAttribute("student", result.get());
			return "edit-student";
		}
		return "redirect:/";
	}
	@GetMapping("/import")
	public String mapReapExcelDatatoDB(Model model) throws IOException {


		if(excelFileHandlerService.getInputStream()==null)
			return "redirect:/";
		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(excelFileHandlerService.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);
		MainClass mainClass = studentReader.getMainClass(sheet);
		List<Student> students = studentReader.getListStudent(sheet, mainClass);
		excelFileHandlerService.setStudents(students);
		model.addAttribute("students", students);
		return "student-preview";
	}
	@GetMapping("/import/save")
	public String saveAll(){
		List<Student> students = excelFileHandlerService.getStudents();
		if(students.isEmpty() || students == null)
			return "redirect:/";
		MainClass mainClass = students.get(0).getMainClass();
		Faculty faculty = mainClass.getFaculty();
		Lecturer lecturer = mainClass.getLecturer();
		if(!facultyService.findById(faculty.getFalcultyId()).isPresent())
			facultyService.save(faculty);
		else
			faculty = facultyService.findById(faculty.getFalcultyId()).get();
		mainClass.setFaculty(faculty);
		if(!lecturerService.existsById(lecturer.getId()))
			lecturerService.save(lecturer);
		else
			lecturer = lecturerService.findById(lecturer.getId());
		mainClass.setLecturer(lecturer);
		if(!mainClassService.findById(mainClass.getClassId()).isPresent())
			mainClassService.save(mainClass);
		else
			mainClass = mainClassService.findById(mainClass.getClassId()).get();
		studentService.saveAll(students);
		excelFileHandlerService.getStudents().clear();
		return "redirect:/student";
	}
	@GetMapping("/import/cancel")
	public String cancelImport() {
		excelFileHandlerService.getStudents().clear();
		return "redirect:/student";
	}
}
