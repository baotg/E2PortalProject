package se.iuh.e2portal.controller;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import se.iuh.e2portal.component.ModuleClassReader;
import se.iuh.e2portal.config.Message;
import se.iuh.e2portal.model.Faculty;
import se.iuh.e2portal.model.ModuleClass;
import se.iuh.e2portal.model.Student;
import se.iuh.e2portal.service.ExcelFileHandlerService;
import se.iuh.e2portal.service.FacultyService;
import se.iuh.e2portal.service.MainClassService;
import se.iuh.e2portal.service.ModuleClassService;
import se.iuh.e2portal.service.StudentService;

@Controller
@RequestMapping("/moduleclass")
public class ModuleClassController {
	
	@Autowired
	private FacultyService facultyService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private MainClassService mainClassService;
	@Autowired
	private ModuleClassReader moduleClassReader;
	@Autowired
	private ModuleClassService moduleClassService;
	@Autowired
	private ExcelFileHandlerService excelFileHandlerService;

    @GetMapping("")
    public String getModuleClass(@PageableDefault(size = 10) Pageable pageable, Model model, @Param("ajax")String ajax) {
      //  model.addAttribute("listClass",moduleClassService.findAll());
        model.addAttribute("faculties", facultyService.findAll());
        if(ajax!=null)
        	return "module-class::module-class";
        return "module-class";
    }
    @GetMapping("/search")
	public String getModuleClasses(@RequestParam("id") String id, Model model) {
		model.addAttribute("listClass", moduleClassService.findByFacultyId(id));
		return "module-class::module-class-table";
	}
    @GetMapping("/import")
	public String mapReadExcelDatatoDB(Model model) throws IOException {
		if(excelFileHandlerService.getInputStream()==null)
			return "module-class::module-class";
		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(excelFileHandlerService.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);
		boolean validate = moduleClassReader.validdateFile(sheet);
		if(!validate) {
			Message msg = Message.FILE_NOT_CORRECT;
			model.addAttribute("msg", msg.getMessage());
			return "redirect:/handle";
		}
		ModuleClass moduleClass = moduleClassReader.getModuleClass(sheet);
		excelFileHandlerService.setModuleClass(moduleClass);
		model.addAttribute("moduleClass", moduleClass);
		return "module-class-preview::module-class-preview";
	}

	@GetMapping("/import/save")
	public String saveAll(Model model){
		ModuleClass moduleClass = excelFileHandlerService.getModuleClass();
		Faculty faculty = moduleClass.getFaculty();
		if(moduleClassService.existsById(moduleClass.getModuleClassId())){
			moduleClass = moduleClassService.findById(moduleClass.getModuleClassId()).get();
		}
		if(facultyService.existsById(faculty.getFacultyId())) {
			faculty = facultyService.findById(faculty.getFacultyId()).get();
		}
		else
			facultyService.save(faculty);
		for(Student student : moduleClass.getStudents()){
			if(!studentService.existsById(student.getId())) {
				if(!mainClassService.existsById(student.getMainClass().getClassId()))
					mainClassService.save(student.getMainClass());
				studentService.save(student);
			}  
			else
				student = studentService.findById(student.getId()).get();
		}
		moduleClassService.save(moduleClass);
		model.addAttribute("faculties", facultyService.findAll());
		model.addAttribute("selectedFaculty",faculty);
		model.addAttribute("listClass", moduleClassService.findByFacultyId(faculty.getFacultyId()));
		return "module-class::module-class";
	}

	@GetMapping("/import/cancel")
	public String cancelImport() {
		return "redirect:/moduleclass";
	}
}
