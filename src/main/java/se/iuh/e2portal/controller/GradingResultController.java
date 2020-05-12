package se.iuh.e2portal.controller;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iuh.e2portal.component.GradingResultReader;
import se.iuh.e2portal.config.Message;
import se.iuh.e2portal.model.*;
import se.iuh.e2portal.service.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/gradingresult")
public class GradingResultController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private MainClassService mainClassService;
	@Autowired
	private GradingResultService gradingResultService;
	@Autowired
	private GradingResultReader gradingResultReader;
	@Autowired
	private ModuleClassService moduleClassService;
	@Autowired
	private ExcelFileHandlerService excelFileHandlerService;
	@Autowired
	private FacultyService facultyService;

	@GetMapping("")
	public String getAll(Model model, @Param("ajax")String ajax){
		//model.addAttribute("gradingresults",gradingResultService.findAll());
		model.addAttribute("faculties", facultyService.findAll());
		if(ajax!=null)
			return "grading-result::grading-result";
		return "grading-result";
	}
	@GetMapping("/search")
	public String getClasses(@RequestParam("id") String id, Model model) {
		model.addAttribute("moduleClasses", moduleClassService.findByFacultyId(id));
		return "grading-result::select-classes";
	}
	@GetMapping("/search/class")
	public String getGradingResultByClassId(@RequestParam("id") String id, Model model) {
		Optional<ModuleClass> moduleClass = moduleClassService.findById(id);
		if(moduleClass.isPresent())
			model.addAttribute("gradingresults",moduleClass.get().getGradingResults());
		return "grading-result::grading-result-table";
	}

	@GetMapping("/{id}")
	public String getGradingResultByStudent(@PathVariable("id") String id, Model model){

		return "redirect:/";
	}

	@GetMapping("/import")
	public String mapReadExcelDatatoDB(Model model) throws IOException {
		if(excelFileHandlerService.getInputStream()==null)
			return "redirect:/";
		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(excelFileHandlerService.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);
		boolean validate = gradingResultReader.validdateFile(sheet);
		if(!validate) {
			Message msg = Message.FILE_NOT_CORRECT;
			model.addAttribute("msg", msg.getMessage());
			return "redirect:/handle";
		}
		ModuleClass moduleClass = gradingResultReader.getModuleClass(sheet);
		List<GradingResult> gradingResults = gradingResultReader.getListGradingResult(sheet,moduleClass);
		excelFileHandlerService.setGradingResults(gradingResults);
		model.addAttribute("gradingresults", gradingResults);
		return "grading-result-preview::grading-result-preview";
	}

	@GetMapping("/import/save")
	public String saveAll(Model model){
		List<GradingResult> gradingResults = excelFileHandlerService.getGradingResults();
		ModuleClass moduleClass = gradingResults.get(0).getModuleClass();	
		Faculty faculty = moduleClass.getFaculty();
		if(!moduleClassService.existsById(moduleClass.getModuleClassId())){
			moduleClassService.save(moduleClass);
		}
		else {
			moduleClass = moduleClassService.findById(moduleClass.getModuleClassId()).get();
		}
		if(facultyService.existsById(faculty.getFacultyId())) {
        	faculty = facultyService.findById(faculty.getFacultyId()).get();
        }
		for(GradingResult gradingResult : gradingResults){
			if(!studentService.existsById(gradingResult.getStudent().getId())) {
				if(!mainClassService.existsById(gradingResult.getStudent().getMainClass().getClassId()))
					mainClassService.save(gradingResult.getStudent().getMainClass());
				studentService.save(gradingResult.getStudent());
			}  
			else
				gradingResult.setStudent(studentService.findById(gradingResult.getStudent().getId()).get());
		}
		gradingResultService.saveAll(gradingResults);
		model.addAttribute("faculties", facultyService.findAll());
		model.addAttribute("moduleClasses", moduleClassService.findByFacultyId(faculty.getFacultyId()));
		model.addAttribute("selectedModuleClass",moduleClass);
		model.addAttribute("selectedFaculty",faculty);
		model.addAttribute("gradingresults",gradingResultService.findByModuleClass(moduleClass.getModuleClassId()));
		return "grading-result::grading-result";
	}

	@GetMapping("/import/cancel")
	public String cancelImport() {
		return "redirect:/gradingresult";
	}
}
