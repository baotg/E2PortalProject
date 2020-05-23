package se.iuh.e2portal.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import se.iuh.e2portal.component.TimeTableReader;
import se.iuh.e2portal.config.Message;
import se.iuh.e2portal.model.*;
import se.iuh.e2portal.service.ExcelFileHandlerService;
import se.iuh.e2portal.service.FacultyService;
import se.iuh.e2portal.service.LecturerService;
import se.iuh.e2portal.service.ModuleClassService;
import se.iuh.e2portal.service.TimeTableService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/timetable")
public class TimeTableController {
	
    @Autowired
    private TimeTableReader timeTableReader;
    @Autowired
    private ModuleClassService moduleClassService;
    @Autowired
    private TimeTableService timeTableService;
    @Autowired
    private LecturerService lecturerService;
    @Autowired
	private ExcelFileHandlerService excelFileHandlerService;
    @Autowired
	private FacultyService facultyService;
    
    @GetMapping("")
    public String getTimeTables(@PageableDefault(size = 10) Pageable pageable, Model model, @Param("ajax")String ajax) {
       // model.addAttribute("timetables",timeTableService.findAll());
        model.addAttribute("faculties", facultyService.findAll());
        if(ajax!=null)
        return "time-table::time-table";
        return "time-table";
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteTimeTable(@RequestParam("id") long id, Model model){
        Optional<TimeTable> result = timeTableService.findById(id);
        if(result.isPresent()){
            timeTableService.delete(result.get());
            model.addAttribute("timetables",timeTableService.findByModuleClassId(result.get().getModuleClassId()));
            return "time-table::time-table-table";
        }
        return "redirect:/";
    }
    @GetMapping("/search")
	public String getClasses(@RequestParam("id") String id, Model model) {
		model.addAttribute("moduleClasses", moduleClassService.findByFacultyId(id));
		return "time-table::select-classes";
	}
	@GetMapping("/search/class")
	public String getStudentByClassId(@RequestParam("id") String id, Model model) {
		Optional<ModuleClass> moduleClass = moduleClassService.findById(id);
		if(moduleClass.isPresent())
			model.addAttribute("timetables",moduleClass.get().getTimeTables());
		return "time-table::time-table-table";
	}
   
    @GetMapping("/import")
    public String mapReadExcelDatatoDB(Model model) throws IOException {
    	if(excelFileHandlerService.getInputStream()==null)
			return "redirect:/?ajax=true";
    	@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(excelFileHandlerService.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        boolean validate = timeTableReader.validdateFile(sheet);
        if(!validate) {
			Message msg = Message.FILE_NOT_CORRECT;
			model.addAttribute("msg", msg.getMessage());
			return "redirect:/handle";
		}
        ModuleClass moduleClass = null;
        List<TimeTable> timeTableList = null;
        try {
        	moduleClass = timeTableReader.getModuleClass(sheet);
        	timeTableList = timeTableReader.getListTimeTable(sheet,moduleClass);
		} catch (Exception e) {
			return "redirect:/handle";
		}
        excelFileHandlerService.setTimeTables(timeTableList);
        model.addAttribute("timeTables", timeTableList);
        return "time-table-preview::time-table-preview";

    }
    @GetMapping("/import/save")
	public String saveAll(Model model){
    	List<TimeTable> timeTables = excelFileHandlerService.getTimeTables();
    	if(timeTables.isEmpty()||timeTables == null)
    		return "redirect:/";
    	ModuleClass moduleClass = timeTables.get(0).getModuleClass();
        Lecturer lecturer = moduleClass.getLecturer();
        Faculty faculty = moduleClass.getFaculty();
        if(!lecturerService.existsById(lecturer.getId())){
            lecturerService.save(lecturer);
        }
        if(facultyService.existsById(faculty.getFacultyId())) {
        	faculty = facultyService.findById(faculty.getFacultyId()).get();
        }
        else
        	facultyService.save(faculty);
        if(!moduleClassService.findById(moduleClass.getModuleClassId()).isPresent()){
            moduleClassService.save(moduleClass);
        }
        else {
            moduleClass = moduleClassService.findById(moduleClass.getModuleClassId()).get();
        }
        timeTableService.saveAll(timeTables);
		excelFileHandlerService.getTimeTables().clear();
		model.addAttribute("timetables", timeTableService.findByModuleClassId(moduleClass.getModuleClassId()));
		model.addAttribute("faculties", facultyService.findAll());
		model.addAttribute("moduleClasses", moduleClassService.findByFacultyId(faculty.getFacultyId()));
		model.addAttribute("selectedModuleClass",moduleClass);
		model.addAttribute("selectedFaculty",faculty);
		return "time-table::time-table";
	}
    
	@GetMapping("/import/cancel")
	public String cancelImport() {
		excelFileHandlerService.getTimeTables().clear();
		return "redirect:/timetable";
	}
}
