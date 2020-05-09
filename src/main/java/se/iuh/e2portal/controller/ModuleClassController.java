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
import se.iuh.e2portal.component.ModuleClassReader;
import se.iuh.e2portal.config.Message;
import se.iuh.e2portal.model.ModuleClass;
import se.iuh.e2portal.model.Student;
import se.iuh.e2portal.service.ExcelFileHandlerService;
import se.iuh.e2portal.service.MainClassService;
import se.iuh.e2portal.service.ModuleClassService;
import se.iuh.e2portal.service.StudentService;

@Controller
@RequestMapping("/moduleclass")
public class ModuleClassController {
	
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
        model.addAttribute("listClass",moduleClassService.findAll());
        if(ajax!=null)
        	return "module-class::module-class";
        return "module-class";
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
			model.addAttribute("listClass",moduleClassService.findAll());
			return "module-class::module-class";
		}
		ModuleClass moduleClass = moduleClassReader.getModuleClass(sheet);
		excelFileHandlerService.setModuleClass(moduleClass);
		model.addAttribute("moduleClass", moduleClass);
		return "module-class-preview";
	}

	@GetMapping("/import/save")
	public String saveAll(){
		ModuleClass moduleClass = excelFileHandlerService.getModuleClass();
		if(moduleClassService.existsById(moduleClass.getModuleClassId())){
			moduleClass = moduleClassService.findById(moduleClass.getModuleClassId()).get();
		}
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
		return "redirect:/moduleclass";
	}

	@GetMapping("/import/cancel")
	public String cancelImport() {
		return "redirect:/moduleclass";
	}
}
