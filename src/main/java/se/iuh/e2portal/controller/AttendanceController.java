package se.iuh.e2portal.controller;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iuh.e2portal.component.AttendanceReader;
import se.iuh.e2portal.config.Message;
import se.iuh.e2portal.model.Attendance;
import se.iuh.e2portal.model.Faculty;
import se.iuh.e2portal.model.ModuleClass;
import se.iuh.e2portal.service.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private ModuleClassService moduleClassService;
	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private AttendanceReader attendanceReader;
	@Autowired
	private ExcelFileHandlerService excelFileHandlerService;
	@Autowired
	private FacultyService facultyService;

	@GetMapping("")
	public String getAll(Model model, @Param("ajax")String ajax){
		model.addAttribute("faculties", facultyService.findAll());
		if(ajax!=null)
		return "attendance::attendance";
		return "attendance";
	}
	@GetMapping("/search")
	public String getClasses(@RequestParam("id") String id, Model model) {
		model.addAttribute("moduleClasses", moduleClassService.findByFacultyId(id));
		return "attendance::select-classes";
	}
	@GetMapping("/search/class")
	public String getAttendanceByClassId(@RequestParam("id") String id, Model model) {
		Optional<ModuleClass> moduleClass = moduleClassService.findById(id);
		if(moduleClass.isPresent())
			model.addAttribute("attendances",moduleClass.get().getAttendances());
		return "attendance::attendance-table";
	}

	@GetMapping("/{id}")
	public String getAttendanceByStudent(@PathVariable("id") String id, Model model){

		return "redirect:/";
	}
	
	@GetMapping("/import")
	public String mapReadExcelDatatoDB(Model model) throws IOException {
		if(excelFileHandlerService.getInputStream()==null)
			return "redirect:/?ajax=true";
		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(excelFileHandlerService.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);
		boolean validate = attendanceReader.validdateFile(sheet);
		if(!validate) {
			Message msg = Message.FILE_NOT_CORRECT;
			model.addAttribute("msg", msg.getMessage());
			return "redirect:/handle";
		}
		ModuleClass moduleClass = attendanceReader.getModuleClass(sheet);
		List<Attendance> attendances = attendanceReader.getListAttendance(sheet,moduleClass);
		excelFileHandlerService.setAttendances(attendances);
		model.addAttribute("attendances",attendances);
		return "attendance-preview::attendance-preview";
	}

	@GetMapping("/import/save")
	public String saveAll(Model model){
		List<Attendance> attendances = excelFileHandlerService.getAttendances();
		if(attendances.isEmpty()||attendances == null)
			return "redirect:/";
		ModuleClass moduleClass = attendances.get(0).getModuleClass();
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
		for(Attendance attendance : attendances){
			if(!studentService.existsById(attendance.getStudent().getId()))
				studentService.save(attendance.getStudent());
			else
				attendance.setStudent(studentService.findById(attendance.getStudent().getId()).get());
		}
		attendanceService.saveAll(attendances);
		excelFileHandlerService.getAttendances().clear();
		model.addAttribute("attendances", attendanceService.findByModuleClassId(moduleClass.getModuleClassId()));
		model.addAttribute("faculties", facultyService.findAll());
		model.addAttribute("moduleClasses", moduleClassService.findByFacultyId(faculty.getFacultyId()));
		model.addAttribute("selectedModuleClass",moduleClass);
		model.addAttribute("selectedFaculty",faculty);
		return "attendance::attendance";
	}

	@GetMapping("/import/cancel")
	public String cancelImport() {
		excelFileHandlerService.getAttendances().clear();
		return "redirect:/attendance";
	}
}
