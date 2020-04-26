package se.iuh.e2portal.controller;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iuh.e2portal.component.AttendanceReader;
import se.iuh.e2portal.config.Message;
import se.iuh.e2portal.model.Attendance;
import se.iuh.e2portal.model.ModuleClass;
import se.iuh.e2portal.service.*;

import java.io.IOException;
import java.util.List;

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

	@GetMapping("")
	public String getAll(Model model){
		model.addAttribute("attendances",attendanceService.findAll());
		return "attendance";
	}

	@GetMapping("/{id}")
	public String getAttendanceByStudent(@PathVariable("id") String id, Model model){

		return "redirect:/student";
	}
	
	@GetMapping("/import")
	public String mapReadExcelDatatoDB(Model model) throws IOException {
		if(excelFileHandlerService.getInputStream()==null)
			return "redirect:/";
		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(excelFileHandlerService.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);
		boolean validate = attendanceReader.validdateFile(sheet);
		if(!validate) {
			Message msg = Message.FILE_NOT_CORRECT;
			model.addAttribute("msg", msg.getMessage());
			model.addAttribute("attendances",attendanceService.findAll());
			return "attendance";
		}
		ModuleClass moduleClass = attendanceReader.getModuleClass(sheet);
		List<Attendance> attendances = attendanceReader.getListAttendance(sheet,moduleClass);
		excelFileHandlerService.setAttendances(attendances);
		model.addAttribute("attendances",attendances);
		return "attendance-preview";
	}

	@GetMapping("/import/save")
	public String saveAll(){
		List<Attendance> attendances = excelFileHandlerService.getAttendances();
		if(attendances.isEmpty()||attendances == null)
			return "redirect:/";
		ModuleClass moduleClass = attendances.get(0).getModuleClass();
		if(!moduleClassService.existsById(moduleClass.getModuleClassId())){
			moduleClassService.save(moduleClass);
		}
		else {
			moduleClass = moduleClassService.findById(moduleClass.getModuleClassId()).get();
		}
		for(Attendance attendance : attendances){
			if(!studentService.existsById(attendance.getStudent().getId()))
				studentService.save(attendance.getStudent());
			else
				attendance.setStudent(studentService.findById(attendance.getStudent().getId()).get());
		}
		attendanceService.saveAll(attendances);
		excelFileHandlerService.getAttendances().clear();
		return "redirect:/attendance";
	}

	@GetMapping("/import/cancel")
	public String cancelImport() {
		excelFileHandlerService.getAttendances().clear();
		return "redirect:/attendance";
	}
}
